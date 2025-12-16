package com.focason.base.consumer;



import com.focason.base.file.repository.FileTaskRepository;
import com.focason.core.domain.FileTaskStatus;
import com.focason.core.domain.ModuleType;
import com.focason.core.exception.FsEntityNotFoundException;
import com.focason.core.exception.FsFileNotFoundException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProcessingImportDataConsumer
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProcessingImportDataConsumer
{
    final Logger logger = LoggerFactory.getLogger(ProcessingImportDataConsumer.class);

    private final FileTaskRepository fileTaskRepository;
    // private final WarehouseService warehouseService;

    @RabbitListener(queues = "processing-imported-data-queue")
    public void consumeMessage(Message message) {
        logger.info("Processing message: {}", message);
        // 通过taskCode来获取任务
        var jsonObject = (JsonObject) JsonParser.parseString(new String(message.getBody()));
        final String taskCode = jsonObject.get("taskCode").getAsString();
        var importTask =
            fileTaskRepository.findImportTask(taskCode).orElseThrow(() -> new FsEntityNotFoundException(taskCode));
        // 根据file module来确定调用的方法
        switch (ModuleType.of(importTask.getFileModule())) {
            case WAREHOUSE:
                // 调用处理方法
                var importDetails = fileTaskRepository.findImportDetails(taskCode);
                importDetails.forEach((entity) -> {
                    try {
                        var warehouseResource = WarehouseResource.builder()
                            .companyCode(entity.getItem1())
                            .warehouseName(entity.getItem2())
                            .warehouseStatus(Integer.valueOf(entity.getItem3()))
                            .remark(entity.getItem4()).build();

                        // warehouseService.create(warehouseResource);
                        entity.setStatus(FileTaskStatus.PROCESSED.getValue());
                    } catch (Exception e) {
                        importTask.setStatus(FileTaskStatus.ERROR.getValue());
                        importTask.setErrorMessage("Import Detail Error");
                        entity.setStatus(FileTaskStatus.ERROR.getValue());
                        entity.setErrorMessage(e.getMessage());
                        logger.error("行处理失败: recordOrder={}, 错误信息={}", entity.getRecordOrder(), e.getMessage(), e);
                    }
                });
                fileTaskRepository.updateImportDetail(importDetails);
                break;
            case TEMPLATE:
            default:
                throw new FsFileNotFoundException(taskCode);
        }
        // 处理完成后更新task（成功或失败）
        fileTaskRepository.updateImportTask(importTask);
    }
}
