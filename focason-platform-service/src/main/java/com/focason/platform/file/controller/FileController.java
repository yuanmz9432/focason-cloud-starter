// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.file.controller;


import com.focason.platform.file.repository.FileTaskRepository;
import com.focason.platform.file.service.FileService;
import com.focason.core.annotation.FsConditionParam;
import com.focason.core.annotation.FsPaginationParam;
import com.focason.core.annotation.FsSortParam;
import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.attribute.FsSort;
import com.focason.core.cloud.service.S3Service;
import com.focason.core.domain.FileMimeType;
import com.focason.core.exception.FsFileNotFoundException;
import com.focason.core.request.FileTaskCreateRequest;
import com.focason.core.request.FileUploadRequest;
import com.focason.core.resource.FileMetadataResource;
import com.focason.core.resource.FileTaskResource;
import com.focason.core.response.FileDownloadResponse;
import com.focason.core.response.FileTaskSearchResponse;
import com.focason.core.response.FileUploadResponse;
import com.focason.core.utility.FsUtilityToolkit;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * FileController
 * <p>
 * REST Controller handling file operations, including S3 presigned URL generation,
 * upload callback processing, and file task management for asynchronous data processing.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RefreshScope
@AllArgsConstructor(onConstructor = @__({
    @Autowired
}))
public class FileController
{
    final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final S3Service s3Service;
    private final FileService fileService;

    private final String FILE_DOWNLOAD_URL = "/api/v1/files/download";
    private final String FILE_UPLOAD_URL = "/api/v1/files/upload";
    private final String FILE_UPLOAD_CALLBACK_URL = "/api/v1/files/callback";
    private final String FILE_TASK_URL = "/api/v1/files/task";
    private final String FILE_SEARCH_URL = "/api/v1/files";

    /**
     * Generates a presigned URL for secure, direct file upload to S3.
     *
     * <p>
     * This endpoint pre-calculates the S3 object key and includes metadata
     * restrictions (like MIME type and original filename) in the presigned URL signature,
     * ensuring clients adhere to the contract during upload.
     * </p>
     *
     * @param originalFileName The original name of the file provided by the user.
     * @param mimeType The MIME type of the file.
     * @return {@link ResponseEntity} containing {@link FileUploadResponse} with the presigned URL,
     *         the generated file ID, and the S3 object key.
     * @throws FsFileNotFoundException if the presigned URL generation fails (e.g., due to configuration).
     */
    @RequestMapping(method = RequestMethod.GET, value = FILE_UPLOAD_URL)
    public ResponseEntity<FileUploadResponse> upload(@RequestParam String originalFileName,
        @RequestParam String mimeType) {

        // 1. Prepare metadata resource with generated file ID and parsed MIME type
        var resource = FileMetadataResource.builder()
            .fileId(FsUtilityToolkit.generateUUID())
            .originalFileName(originalFileName)
            .mimeType(FileMimeType.of(mimeType))
            .build();

        // 2. Determine the unique and full S3 object key (path)
        var objectKey = fileService.generateObjectKey(resource);

        // 3. Generate the presigned PUT URL using the full object key and mandatory metadata
        return s3Service.generatePutUrl(resource)
            .map(url -> ResponseEntity.ok(FileUploadResponse.builder()
                .fileId(resource.getFileId())
                .objectKey(objectKey)
                .presignedUrl(url.toString())
                .build()))
            .orElseThrow(() -> new FsFileNotFoundException(originalFileName));
    }

    /**
     * Generates a presigned URL for secure file download from S3.
     *
     * @param objectKey The unique key of the object in the S3 bucket.
     * @return {@link ResponseEntity} containing {@link FileDownloadResponse} with the presigned URL.
     * @throws FsFileNotFoundException if the object key does not correspond to a valid S3 object.
     */
    @RequestMapping(method = RequestMethod.GET, value = FILE_DOWNLOAD_URL)
    public ResponseEntity<FileDownloadResponse> download(@RequestParam String objectKey) {
        // 1. Count the download number and return the original file name.
        var originalFileName = fileService.download(objectKey);
        // 2. Delegate to S3Service to generate the time-limited GET URL
        return s3Service.generateGetUrl(objectKey, originalFileName)
            .map(url -> ResponseEntity.ok(FileDownloadResponse.builder()
                .presignedUrl(url.toString())
                .build()))
            .orElseThrow(() -> new FsFileNotFoundException(objectKey));
    }

    /**
     * Callback endpoint invoked by the frontend after a successful direct upload to S3.
     *
     * <p>
     * This method persists the file metadata (retrieved via HeadObject using the
     * provided S3 key) into the database (BASE_004_FILE_METADATA).
     * </p>
     *
     * @param request The upload request containing the S3 object key and file ID.
     * @return A standard empty response on successful metadata creation.
     */
    @RequestMapping(method = RequestMethod.POST, value = FILE_UPLOAD_CALLBACK_URL)
    public ResponseEntity<Void> callback(@RequestBody FileUploadRequest request) {
        // Convert the request DTO to the internal resource model and delegate creation
        fileService.create(FsUtilityToolkit.convert(request, FileMetadataResource.class));
        return ResponseEntity.ok().build();
    }

    /**
     * Creates a new file processing task (e.g., for bulk import, data analysis).
     *
     * <p>
     * This workflow outlines the typical asynchronous file processing:
     * <ol>
     * <li>Client uploads file to S3 (via presigned URL).</li>
     * <li>Client calls back to persist metadata.</li>
     * <li>Client calls this task endpoint to initiate processing.</li>
     * <li>Backend creates a task record.</li>
     * <li>Backend sends a message to the Message Queue (e.g., Kafka/SQS).</li>
     * <li>A dedicated consumer service picks up the message and executes the task (validation, parsing, DB
     * insertion).</li>
     * </ol>
     * </p>
     *
     * @param request The request containing details for the file task to be created.
     * @return A standard empty response upon successful task initiation.
     */
    @RequestMapping(method = RequestMethod.POST, value = FILE_TASK_URL)
    public ResponseEntity<Void> createImportTask(@RequestBody FileTaskCreateRequest request) {
        // 1. Create task record in the database
        var resource = fileService.createFileTask(FsUtilityToolkit.convert(request, FileTaskResource.class));

        // 2. Send message to the message queue to trigger asynchronous processing
        fileService.sendMessageQueue(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * Search file processing tasks based on specified conditions.
     *
     * @param condition The search condition DTO, automatically resolved from request parameters.
     * @param pagination The pagination parameters (page, size).
     * @param fsSort The sorting criteria.
     * @return The result set of matching file tasks, including pagination count.
     */
    @RequestMapping(method = RequestMethod.GET, value = FILE_SEARCH_URL)
    public ResponseEntity<FsResultSet<FileTaskSearchResponse>> search(
        @FsConditionParam FileTaskRepository.Condition condition,
        @FsPaginationParam FsPagination pagination,
        @FsSortParam(allowedValues = {}) FsSort fsSort) {
        if (condition == null) {
            condition = FileTaskRepository.Condition.DEFAULT;
        }
        var sort = FileTaskRepository.Sort.fromFsSort(fsSort);

        // Delegate search and retrieve the result set
        var fileTaskResource = fileService.search(condition, pagination, sort);

        // Convert the internal resource list to the search response DTO list
        return ResponseEntity.ok(new FsResultSet<>(
            FsUtilityToolkit.convert(fileTaskResource.getData(), FileTaskSearchResponse.class),
            fileTaskResource.getCount()));
    }
}
