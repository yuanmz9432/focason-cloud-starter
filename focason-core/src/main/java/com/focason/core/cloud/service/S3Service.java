// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.cloud.service;


import com.focason.core.exception.FsErrorCode;
import com.focason.core.exception.FsException;
import com.focason.core.exception.FsFileNotFoundException;
import com.focason.core.resource.FileMetadataResource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

/**
 * Service class for interacting with AWS S3, managing uploads, downloads, and pre-signed URLs.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
public class S3Service
{
    final Logger logger = LoggerFactory.getLogger(S3Service.class);

    private final static String S3_PATH_SEPARATOR = "/";
    private final S3Presigner s3Presigner;
    private final S3Client s3Client;
    private String bucketName;
    private String prefix;
    private long preSignedUrlValidMinutes;

    /**
     * Retrieves the configured prefix followed by a path separator if the prefix is not empty.
     *
     * @return The S3 path prefix with a trailing slash, or an empty string.
     */
    private String getPrefix() {
        return prefix != null && !prefix.isEmpty() ? prefix + S3_PATH_SEPARATOR : "";
    }

    /**
     * Creates a pre-signed URL PUT request, including custom metadata headers.
     *
     * @param resource File metadata resource containing details like file ID, original name, and MIME type.
     * @return {@link PresignedPutObjectRequest}
     */
    private PresignedPutObjectRequest getPresignedPutObjectRequest(FileMetadataResource resource) {
        // 1. Define custom metadata map
        // The key must be in lowercase and prefixed with "x-amz-meta-".
        // We use "original-file-name" to store the user's original file name.
        Map<String, String> metadata = new HashMap<>();

        // Add original file name as custom S3 metadata
        metadata.put("x-amz-meta-original-file-name", resource.getOriginalFileName());
        // Add file id as custom S3 metadata
        metadata.put("x-amz-meta-file-id", resource.getFileId());

        // 2. Build the PutObjectRequest with bucket, key, and metadata
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            // Use the full objectKey which already includes the configured prefix (if any)
            .key(getPrefix() + resource.getObjectKey())
            // Force the client to use a specific MIME type header during upload
            .contentType(resource.getMimeType().getValue())
            // Set the custom metadata. These headers are included in the signature.
            .metadata(metadata)
            .build();

        // 3. Build the PutObjectPresignRequest
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
            // Set the signature duration (validity of the URL)
            .signatureDuration(Duration.ofMinutes(preSignedUrlValidMinutes))
            .putObjectRequest(objectRequest)
            .build();

        // 4. Generate the pre-signed request
        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        // Log the required headers for the client (optional, but helpful for debugging)
        logger.debug("Presigned URL requires custom header: x-amz-meta-original-file-name={}",
            resource.getOriginalFileName());

        return presignedRequest;
    }

    /**
     * Creates a pre-signed URL PUT request using only the object key.
     *
     * @param objectKey S3 object key.
     * @return {@link PresignedPutObjectRequest}
     */
    private PresignedPutObjectRequest getPresignedPutObjectRequest(String objectKey) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(preSignedUrlValidMinutes))
            .putObjectRequest(objectRequest)
            .build();

        return s3Presigner.presignPutObject(presignRequest);
    }

    /**
     * Creates a pre-signed URL GET request.
     *
     * @param objectKey S3 object key.
     * @return {@link PresignedGetObjectRequest}
     */
    private PresignedGetObjectRequest getPresignedGetObjectRequest(String objectKey) {
        GetObjectRequest objectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(preSignedUrlValidMinutes))
            .getObjectRequest(objectRequest)
            .build();

        return s3Presigner.presignGetObject(presignRequest);
    }

    /**
     * Uploads a file to S3 from a local path.
     *
     * @param objectKey S3 object key.
     * @param path Local object path.
     */
    public void uploadObject(String objectKey, String path) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(objectKey)
            .build();
        s3Client.putObject(objectRequest, RequestBody.fromFile(Path.of(path)));
    }

    /**
     * Uploads a file to S3 using a byte array.
     *
     * @param objectKey S3 object key.
     * @param bytes File bytes.
     */
    public void uploadObject(String objectKey, byte[] bytes) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(objectKey)
            .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(bytes));
    }

    /**
     * Downloads a file from S3 to a local destination path.
     *
     * @param objectKey S3 object key.
     * @param path Destination path for download.
     */
    public void getObject(String objectKey, String path) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();
        try {
            var s3Object = s3Client.getObjectAsBytes(getObjectRequest);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(s3Object.asByteArray());
            } catch (IOException e) {
                // Re-throw IO exceptions as a general internal error.
                throw new FsException(FsErrorCode.INTERNAL_SERVER_ERROR,
                    "Error writing file to local path: " + e.getMessage(), e);
            }
        } catch (NoSuchKeyException ex) {
            // If the file matching the key does not exist in the S3 bucket
            throw new FsFileNotFoundException(objectKey);
        } catch (Exception ex) {
            // Catch all other S3 or general exceptions
            throw new FsException(FsErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    /**
     * Checks for the existence of an object with the given key in S3.
     *
     * @param objectKey S3 object key.
     * @return File existence status in S3.
     */
    public boolean doesObjectExist(String objectKey) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();
        try {
            // Attempt a quick synchronous check, headObject is generally better but
            // getObject also throws NoSuchKeyException.
            s3Client.getObject(getObjectRequest);
        } catch (NoSuchKeyException ex) {
            // If the file matching the key does not exist in the S3 bucket
            return false;
        } catch (S3Exception e) {
            throw new FsException(FsErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return true;
    }

    /**
     * Generates a pre-signed URL for file download.
     *
     * @param objectKey S3 object key.
     * @return Pre-signed URL, or {@code Optional.empty()} if the file does not exist in S3.
     */
    public Optional<URL> generateGetUrl(String objectKey) {
        if (doesObjectExist(objectKey)) {
            return Optional.of(getPresignedGetObjectRequest(objectKey).url());
        }
        return Optional.empty();
    }

    /**
     * Generates a pre-signed URL for file upload, using metadata from the resource.
     *
     * @param resource File metadata resource.
     * @return Pre-signed URL.
     */
    public Optional<URL> generatePutUrl(FileMetadataResource resource) {
        return Optional.of(getPresignedPutObjectRequest(resource).url());
    }

    /**
     * Generates a pre-signed URL for file upload using a plain object key.
     *
     * @param objectKey S3 object key.
     * @return Pre-signed URL.
     */
    public Optional<URL> generatePutUrl(String objectKey) {
        return Optional.of(getPresignedPutObjectRequest(objectKey).url());
    }

    /**
     * Generates a pre-signed URL for file upload, changing the file name part of the object key.
     *
     * @param objectKey S3 object key.
     * @param newFileName New file name to be used in the object key.
     * @return Pre-signed URL.
     */
    public URL generatePutUrl(String objectKey, String newFileName) {
        return getPresignedPutObjectRequest(changeObjectKeyByNewFileName(objectKey, newFileName)).url();
    }

    /**
     * Changes the file name within the S3 object key, preserving the path and extension.
     *
     * @param objectKey S3 object key.
     * @param newFileName New file name.
     * @return The updated S3 object key string.
     */
    private String changeObjectKeyByNewFileName(String objectKey, String newFileName) {
        String[] objectKeyList = objectKey.split(S3_PATH_SEPARATOR);
        // Extract the file extension
        Optional<String> extension = Optional.ofNullable(objectKeyList[objectKeyList.length - 1])
            .filter(f -> f.contains("."))
            .map(f -> f.substring(f.lastIndexOf(".") + 1));
        // Replace the file name part, appending the extension if it existed
        objectKeyList[objectKeyList.length - 1] = extension.map(s -> newFileName + "." + s).orElse(newFileName);
        return String.join(S3_PATH_SEPARATOR, objectKeyList);
    }

    /**
     * Creates a pre-signed URL GET request for downloading an object with a specified filename (for browser download).
     *
     * @param objectKey S3 object key.
     * @param fileName File name to be suggested to the browser/client.
     * @return {@link PresignedGetObjectRequest}
     */
    private PresignedGetObjectRequest getPresignedGetObjectRequest(String objectKey, String fileName) {
        GetObjectRequest objectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            // Set the Content-Disposition header to force download and suggest a file name
            .responseContentDisposition("attachment;filename=" + fileName)
            .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(preSignedUrlValidMinutes))
            .getObjectRequest(objectRequest)
            .build();
        return s3Presigner.presignGetObject(presignRequest);
    }

    /**
     * Generates a pre-signed URL for file download, suggesting a specific filename.
     *
     * @param objectKey S3 object key.
     * @param originalFileName File name to be used in the Content-Disposition header.
     * @return Pre-signed URL, or {@code Optional.empty()} if the file does not exist in S3.
     */
    public Optional<URL> generateGetUrl(String objectKey, String originalFileName) {
        if (doesObjectExist(objectKey)) {
            return Optional.of(getPresignedGetObjectRequest(objectKey, originalFileName).url());
        }
        return Optional.empty();
    }

    /**
     * Deletes a file from S3 if it exists.
     *
     * @param objectKey S3 object key.
     */
    public void deleteObject(String objectKey) {
        if (doesObjectExist(objectKey)) {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(getPrefix() + objectKey)
                .build();
            s3Client.deleteObject(deleteObjectRequest);
        }
    }

    /**
     * Retrieves the metadata for an S3 object (using HeadObject).
     *
     * @param objectKey S3 object key.
     * @return Map containing custom metadata and standard headers (Content-Length, Content-Type).
     */
    public Map<String, String> getFileMetadata(String objectKey) {
        HeadObjectRequest request = HeadObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();

        HeadObjectResponse headResponse = s3Client.headObject(request);
        Map<String, String> metadata = new HashMap<>(headResponse.metadata());

        // Add standard headers
        metadata.put("Content-Length", headResponse.contentLength().toString());
        metadata.put("Content-Type", headResponse.contentType());
        return metadata;
    }
}
