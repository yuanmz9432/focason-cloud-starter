package com.focason.base.consumer;



import com.focason.base.file.repository.FileTaskRepository;
import com.focason.core.cloud.service.S3Service;
import com.focason.core.domain.FileTaskStatus;
import com.focason.core.domain.FileType;
import com.focason.core.entity.Io002TaskDetailEntity;
import com.focason.core.exception.FsEntityNotFoundException;
import com.focason.core.queue.ProcessingImportedDataQueue;
import com.focason.core.utility.FsFileToolKit;
import com.focason.core.utility.FsUtilityToolkit;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileImportConsumer
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FileImportConsumer
{
    final Logger logger = LoggerFactory.getLogger(FileImportConsumer.class);

    private final S3Service s3Service;
    private final FileTaskRepository fileTaskRepository;
    private final RabbitTemplate rabbitTemplate;

    private static final Map<String, Field> fieldCache = new HashMap<>();
    private static final int ITEM_COUNT = 20;

    static {
        for (int i = 1; i <= 20; i++) {
            Field field;
            try {
                field = Io002TaskDetailEntity.class.getDeclaredField("item" + i);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            field.setAccessible(true);
            fieldCache.put("item" + i, field);
        }
    }

    @RabbitListener(queues = "file-import-queue")
    public void consumeMessage(Message message) {
        logger.info("Processing message: {}", message);
        // Parse JSON message
        JsonObject jsonObject = JsonParser.parseString(new String(message.getBody())).getAsJsonObject();
        String taskCode = jsonObject.get("taskCode").getAsString();
        String fileName = jsonObject.get("fileName").getAsString();
        String objectKey = jsonObject.get("filePath").getAsString();
        Integer fileType = jsonObject.get("fileType").getAsInt();

        // Retrieve import task entity from database
        var importTaskEntity = fileTaskRepository.findImportTask(taskCode)
            .orElseThrow(() -> new FsEntityNotFoundException(taskCode));

        // Local directory
        var temporaryFile = String.format("download/%s", fileName);
        try {
            // Download file from S3 to local directory
            s3Service.getObject(objectKey, temporaryFile);

            // 判断文件类型，进行文件读取
            switch (FileType.of(fileType)) {
                case CSV:
                case EXCEL:
                    processingExcel(temporaryFile, taskCode);
                    break;
                default:
                    break;
            }

            // Update task status to IMPORTED
            importTaskEntity.setStatus(FileTaskStatus.IMPORTED.getValue());
            fileTaskRepository.updateImportTask(importTaskEntity);

            // Send message to processing-imported-data-queue
            var queue = ProcessingImportedDataQueue.builder().taskCode(taskCode).build();
            rabbitTemplate.convertAndSend("processing-imported-data-queue",
                FsUtilityToolkit.convertObjectToJson(queue));
        } catch (Exception e) {
            // 文件读取失败，更新读取任务状态（失败）
            importTaskEntity.setStatus(FileTaskStatus.ERROR.getValue());
            importTaskEntity.setErrorMessage(e.getMessage());
            fileTaskRepository.updateImportTask(importTaskEntity);
            throw new RuntimeException(e);
        } finally {
            // Delete the temporary file if it exists
            FsFileToolKit.deleteTemporaryFile(temporaryFile);
        }
    }

    private void processingExcel(String downloadTo, String taskCode) {
        // 读取EXCEL文件中的内容
        var fileJsonObject = FsFileToolKit.readExcelAsJson(downloadTo, 0, 1);
        var entities = new ArrayList<Io002TaskDetailEntity>();

        // 遍历Excel数据行
        fileJsonObject.entrySet().forEach(rowEntry -> {
            var entity = new Io002TaskDetailEntity();
            entity.setTaskCode(taskCode);
            entity.setRecordOrder(Integer.parseInt(rowEntry.getKey()));
            entity.setStatus(FileTaskStatus.PROCESSED.getValue());
            try {
                // 处理该行数据
                processRowData(rowEntry, entity);
            } catch (Exception e) {
                entity.setStatus(FileTaskStatus.ERROR.getValue());
                entity.setErrorMessage(e.getMessage());
                logger.error("行处理失败: recordOrder={}, 错误信息={}", entity.getRecordOrder(), e.getMessage(), e);
            }
            entities.add(entity);
        });

        // 批量插入数据库
        fileTaskRepository.saveTaskDetail(entities);
    }

    private void processRowData(Map.Entry<String, JsonElement> rowEntry, Io002TaskDetailEntity entity)
        throws IllegalAccessException {
        JsonObject rowObject = rowEntry.getValue().getAsJsonObject();
        int columnIndex = 1;

        for (Map.Entry<String, JsonElement> cellEntry : rowObject.entrySet()) {
            if (columnIndex > ITEM_COUNT)
                break;

            String cellValue = cellEntry.getValue().getAsString();
            Field field = fieldCache.get(String.format("item%s", columnIndex));
            if (field != null) {
                field.set(entity, cellValue);
            }
            columnIndex++;
        }
    }
}
