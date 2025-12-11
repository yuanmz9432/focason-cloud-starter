package com.focason.core.cloud.service;



import com.focason.core.exception.FsErrorCode;
import com.focason.core.exception.FsException;
import com.focason.core.exception.FsFileNotFoundException;
import com.focason.core.resource.FileMetadataResource;
import java.io.FileOutputStream;
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

    private String getPrefix() {
        return prefix != null && !prefix.isEmpty() ? prefix + S3_PATH_SEPARATOR : "";
    }

    /**
     * 証明付きURL PUTリクエスト作成
     *
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

        // 4. Generate the presigned request
        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        // Log the required headers for the client (optional, but helpful for debugging)
        logger.debug("Presigned URL requires custom header: x-amz-meta-original-file-name={}",
            resource.getOriginalFileName());

        return presignedRequest;
    }

    /**
     * 証明付きURL PUTリクエスト作成
     *
     * @param objectKey S3オブジェクトキー
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
     * 証明付きURL GETリクエスト作成
     *
     * @param objectKey S3オブジェクトキー
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
     * ファイルをS3にアップロードする
     *
     * @param objectKey S3オブジェクトキー
     * @param path ローカルオブジェクトパス
     */
    public void uploadObject(String objectKey, String path) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(objectKey)
            .build();
        s3Client.putObject(objectRequest, RequestBody.fromFile(Path.of(path)));
    }

    /**
     * ファイルをS3にアップロードする
     *
     * @param objectKey S3オブジェクトキー
     * @param bytes ファイルバイト
     */
    public void uploadObject(String objectKey, byte[] bytes) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(objectKey)
            .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(bytes));
    }

    /**
     * ファイルをS3からダウンロードする
     *
     * @param objectKey S3オブジェクトキー
     * @param path ダウンロード先
     */
    public void getObject(String objectKey, String path) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();
        try {
            var s3Object = s3Client.getObjectAsBytes(getObjectRequest);
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(s3Object.asByteArray());
            fos.close();
        } catch (NoSuchKeyException ex) {
            // S3バケットにキーと一致するファイルが存在しない場合
            throw new FsFileNotFoundException(objectKey);
        } catch (Exception ex) {
            throw new FsException(FsErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    /**
     * S3内にオブジェクトキーのファイル存在確認
     *
     * @param objectKey S3オブジェクトキー
     * @return S3内のファイル存在有無
     */
    public boolean doesObjectExist(String objectKey) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();
        try {
            s3Client.getObject(getObjectRequest);
        } catch (NoSuchKeyException ex) {
            // S3バケットにキーと一致するファイルが存在しない場合
            return false;
        } catch (S3Exception e) {
            throw new FsException(FsErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return true;
    }

    /**
     * ファイルダウンロード用の証明付きURL作成
     *
     * @param objectKey S3オブジェクトキー
     * @return 署名付きURL or S3にファイルが存在しない場合はNULL
     */
    public Optional<URL> generateGetUrl(String objectKey) {
        if (doesObjectExist(objectKey)) {
            return Optional.of(getPresignedGetObjectRequest(objectKey).url());
        }
        return Optional.empty();
    }

    /**
     * ファイルアップロード用の証明付きURL作成
     *
     * @return 署名付きURL
     */
    public Optional<URL> generatePutUrl(FileMetadataResource resource) {
        return Optional.of(getPresignedPutObjectRequest(resource).url());
    }

    /**
     * ファイルアップロード用の証明付きURL作成
     *
     * @param objectKey S3オブジェクトキー
     * @return 署名付きURL
     */
    public Optional<URL> generatePutUrl(String objectKey) {
        return Optional.of(getPresignedPutObjectRequest(objectKey).url());
    }

    /**
     * ファイルアップロード用の証明付きURL作成
     * fileNameからnewFileNameにファイル名を変更してアップロードする。
     *
     * @param objectKey S3オブジェクトキー
     * @param newFileName 新しいファイル名
     * @return 署名付きURL
     */
    public URL generatePutUrl(String objectKey, String newFileName) {
        return getPresignedPutObjectRequest(changeObjectKeyByNewFileName(objectKey, newFileName)).url();
    }

    /**
     * S3オブジェクトキーからファイル名を変更する。
     *
     * @param objectKey S3オブジェクトキー
     * @param newFileName 新しいファイル名
     * @return 署名付きURL
     */
    private String changeObjectKeyByNewFileName(String objectKey, String newFileName) {
        String[] objectKeyList = objectKey.split(S3_PATH_SEPARATOR);
        Optional<String> extension = Optional.ofNullable(objectKeyList[objectKeyList.length - 1])
            .filter(f -> f.contains("."))
            .map(f -> f.substring(f.lastIndexOf(".") + 1));
        objectKeyList[objectKeyList.length - 1] = extension.map(s -> newFileName + "." + s).orElse(newFileName);
        return String.join(S3_PATH_SEPARATOR, objectKeyList);
    }

    /**
     * 証明付きURL GETリクエスト作成
     *
     * @param objectKey S3オブジェクトキー
     * @param fileName ファイル名
     * @return {@link PresignedGetObjectRequest}
     */
    private PresignedGetObjectRequest getPresignedGetObjectRequest(String objectKey, String fileName) {
        GetObjectRequest objectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .responseContentDisposition("attachment;filename=" + fileName)
            .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(preSignedUrlValidMinutes))
            .getObjectRequest(objectRequest)
            .build();
        return s3Presigner.presignGetObject(presignRequest);
    }

    /**
     * ファイルダウンロード用の証明付きURL作成
     *
     * @param objectKey S3オブジェクトキー
     * @param originalFileName ファイル名
     * @return 署名付きURL or S3にファイルが存在しない場合はNULL
     */
    public Optional<URL> generateGetUrl(String objectKey, String originalFileName) {
        if (doesObjectExist(objectKey)) {
            return Optional.of(getPresignedGetObjectRequest(objectKey, originalFileName).url());
        }
        return Optional.empty();
    }

    /**
     * ファイルをS3から削除
     *
     * @param objectKey S3オブジェクトキー
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
     * ファイルアップロード用の証明付きURL作成
     *
     * @return 署名付きURL
     */
    public Map<String, String> getFileMetadata(String objectKey) {
        HeadObjectRequest request = HeadObjectRequest.builder()
            .bucket(bucketName)
            .key(getPrefix() + objectKey)
            .build();

        HeadObjectResponse headResponse = s3Client.headObject(request);
        Map<String, String> metadata = new HashMap<>(headResponse.metadata());

        metadata.put("Content-Length", headResponse.contentLength().toString());
        metadata.put("Content-Type", headResponse.contentType());
        return metadata;
    }
}
