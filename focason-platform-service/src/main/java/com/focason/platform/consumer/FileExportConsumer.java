package com.focason.platform.consumer;



import com.focason.platform.file.repository.FileTaskRepository;
import com.focason.core.domain.FileTaskStatus;
import com.focason.core.exception.FsEntityNotFoundException;
import com.focason.core.handler.ExportHandler;
import com.focason.core.handler.ExportHandlerFactory;
import com.focason.core.resource.FileResource;
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
 * FileExportConsumer
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FileExportConsumer
{
    final Logger logger = LoggerFactory.getLogger(FileExportConsumer.class);

    private final FileTaskRepository repository;
    private final ExportHandlerFactory handlerFactory;

    @RabbitListener(queues = "file-export-queue")
    public void consumeMessage(Message message) {
        logger.info("Processing message: {}", message);
        // Parse JSON message
        JsonObject jsonObject = JsonParser.parseString(new String(message.getBody())).getAsJsonObject();
        String taskCode = jsonObject.get("taskCode").getAsString();

        var task = repository.findExportTask(taskCode)
            .orElseThrow(() -> new FsEntityNotFoundException(FileResource.class, taskCode));

        // 关键点：根据页面类型获取对应的处理器
        ExportHandler handler = handlerFactory.getHandler(task.getFileModule());

        try {
            handler.export(taskCode);
            task.setStatus(FileTaskStatus.PROCESSED.getValue());
        } catch (Exception e) {
            task.setStatus(FileTaskStatus.ERROR.getValue());
            task.setErrorMessage(e.getMessage());
        }

        repository.modify(task);
    }
}
