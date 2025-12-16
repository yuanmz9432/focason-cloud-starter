// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.file.service;


import com.focason.platform.file.repository.FileRepository;
import com.focason.platform.file.repository.FileTaskRepository;
import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.cloud.service.S3Service;
import com.focason.core.domain.*;
import com.focason.core.entity.Base007FileMetadataEntity;
import com.focason.core.entity.Io001FileTaskEntity;
import com.focason.core.exception.FsFileNotFoundException;
import com.focason.core.exception.FsValidationErrorException;
import com.focason.core.queue.FileExportMessage;
import com.focason.core.queue.FileImportQueue;
import com.focason.core.resource.FileMetadataResource;
import com.focason.core.resource.FileResource;
import com.focason.core.resource.FileTaskResource;
import com.focason.core.utility.FsUtilityToolkit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 * FileService
 * <p>
 * Provides core business logic for file handling, including S3 object key generation,
 * file metadata persistence, and asynchronous file task management (import/export).
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FileService
{
    final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileTaskRepository fileTaskRepository;
    private final FileRepository repository;
    private final RabbitTemplate rabbitTemplate;
    private final S3Service s3Service;

    /**
     * Generates the S3 object key for standard user-uploaded files.
     * The key format is typically based on the current user ID and the file's unique ID.
     *
     * @param resource The {@link FileMetadataResource} containing the file ID.
     * @return The generated S3 object key (e.g., "user-id/file-uuid").
     */
    public String generateObjectKey(FileMetadataResource resource) {
        String currentUid = MDC.get(FsUtilityToolkit.CLAIMS_SUB);
        var objectKey = currentUid + "/" + resource.getFileId();
        resource.setObjectKey(objectKey);
        return objectKey;
    }

    /**
     * Generates the S3 object key for internal or system-specific files based on module type.
     *
     * @param resource The {@link FileResource} containing module and file details.
     * @return The generated S3 object key.
     * @throws FsValidationErrorException if the provided file module is invalid.
     */
    public String generateObjectKey(FileResource resource) {

        ModuleType type = ModuleType.of(resource.getFileModule());
        if (type == null) {
            throw new FsValidationErrorException(String.format("Invalid module type: %s", resource.getFileModule()));
        }

        String objectKey = null;
        switch (ModuleType.of(resource.getFileModule())) {
            case TEMPLATE:
                // Format: templates/filename.ext
                objectKey = String.format("templates/%s", resource.getFileName());
                break;
            case WAREHOUSE:
                // Format: [BusinessUnit]/warehouse/[TaskCode]_[FileName]
                objectKey =
                    String.format("%s/warehouse/%s_%s", resource.getBusinessUnit(), resource.getTaskCode(),
                        resource.getFileName());
                break;
            default:
                break;
        }
        return objectKey;
    }

    /**
     * Creates a new file processing task (e.g., for bulk import).
     *
     * @param resource The details of the file task to be created.
     * @return The created {@link FileTaskResource} with updated internal fields.
     */
    @Transactional
    public FileTaskResource createFileTask(FileTaskResource resource) {

        var entity = FsUtilityToolkit.convert(resource, Io001FileTaskEntity.class);

        if (entity.getTaskCode() == null) {
            entity.setTaskCode(FsUtilityToolkit.generateUUID());
        }
        if (entity.getFilePath() == null) {
            // Generate the file path (S3 object key) if not provided
            var objectKey = generateObjectKey(FsUtilityToolkit.convert(resource, FileResource.class));
            entity.setFilePath(objectKey);
        }
        entity.setReceiveDate(LocalDate.now());
        // TODO Use Redis or another atomic counter service to ensure unique, sequential process order
        entity.setProcessOrder(1);
        entity.setStatus(FileTaskStatus.PENDING.getValue());

        // Persist the import task record
        fileTaskRepository.save(entity);

        return FsUtilityToolkit.convert(entity, FileTaskResource.class);
    }

    /**
     * Sends a file task message to the appropriate Message Queue (MQ) after the database transaction commits.
     * This ensures atomicity: task record is created *before* the processing message is sent.
     *
     * @param resource The {@link FileTaskResource} containing task details.
     */
    @Transactional
    public void sendMessageQueue(FileTaskResource resource) {
        String routingKey = null;
        Object messageQueue = null;

        // Determine the message payload and routing key based on the task type
        switch (FileTaskType.of(resource.getTaskType())) {
            case EXPORT:
                messageQueue = FileExportMessage.builder()
                    .taskCode(resource.getTaskCode()).build();
                routingKey = "file-export-queue";
                break;
            case IMPORT:
                messageQueue = FileImportQueue.builder()
                    .taskCode(resource.getTaskCode())
                    .fileName(resource.getFileName())
                    .fileType(resource.getFileType())
                    .filePath(resource.getFilePath()).build();
                routingKey = "file-import-queue";
                break;
            default:
                // Should handle unsupported task types or throw
                break;
        }

        // Use TransactionSynchronizationManager to delay message sending until transaction commit
        String finalRoutingKey = routingKey;
        Object finalMessageQueue = messageQueue;

        // Register synchronization to execute logic after successful commit
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                // Send the message to the Message Queue to trigger processing/validation
                rabbitTemplate.convertAndSend(finalRoutingKey,
                    FsUtilityToolkit.convertObjectToJson(finalMessageQueue));
            }
        });
    }

    /**
     * Searches file task records based on provided conditions, pagination, and sorting.
     *
     * @param condition The search conditions.
     * @param pagination The pagination information.
     * @param sort The sorting criteria.
     * @return A {@link FsResultSet} containing the found {@link FileTaskResource} and total count.
     */
    public FsResultSet<FileTaskResource> search(FileTaskRepository.Condition condition, FsPagination pagination,
        FileTaskRepository.Sort sort) {
        var resultSet = fileTaskRepository.findAll(condition, pagination, sort);
        var resources = FsUtilityToolkit.convert(resultSet.getData(), FileTaskResource.class);
        return new FsResultSet<>(resources, resultSet.getCount());
    }

    /**
     * Persists the file metadata into the database (BASE_007_FILE_METADATA) after a successful S3 upload.
     * This method retrieves necessary metadata (like original filename, size) directly from S3.
     *
     * @param resource The {@link FileMetadataResource} containing the S3 object key.
     */
    @Transactional
    public void create(FileMetadataResource resource) {
        String currentUid = MDC.get(FsUtilityToolkit.CLAIMS_SUB);
        // Retrieve metadata headers from the S3 object itself (HeadObject operation)
        var metadata = s3Service.getFileMetadata(resource.getObjectKey());

        // Map S3 metadata and current user context to the database entity
        var entity = new Base007FileMetadataEntity();
        entity.setUid(currentUid);
        // Assuming custom metadata keys are consistent with those set during presigned URL generation
        entity.setFileId(metadata.get("file-id"));
        entity.setOriginalFileName(metadata.get("original-file-name"));
        entity.setObjectKey(resource.getObjectKey());
        entity.setMimeType(FileMimeType.of(metadata.get("Content-Type")).getValue());
        entity.setExtension(FileMimeType.of(metadata.get("Content-Type")).getExtension());
        entity.setStatus(Switch.ON.getValue());
        entity.setSize(Long.valueOf(metadata.get("Content-Length")));
        entity.setDownloadCount(0); // Initialize download counter

        repository.save(entity);
    }

    /**
     * Updates the download count and last download timestamp for a file.
     *
     * @param objectKey The S3 object key of the file being downloaded.
     * @throws FsFileNotFoundException if no file metadata exists for the given object key.
     */
    public String download(String objectKey) {
        // Find the metadata entity by S3 object key
        var entity =
            repository.findFileByObjectKey(objectKey).orElseThrow(() -> new FsFileNotFoundException(objectKey));

        // Update download metrics
        entity.setDownloadCount(entity.getDownloadCount() + 1);
        entity.setLastDownloadDatetime(LocalDateTime.now());

        repository.modify(entity);

        return entity.getOriginalFileName();
    }
}
