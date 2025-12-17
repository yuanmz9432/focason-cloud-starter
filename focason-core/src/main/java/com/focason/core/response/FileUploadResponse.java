package com.focason.core.response;

/**
 * FileUploadResponse
 *
 * @param fileId ファイルID
 * @param objectKey オブジェクトキー
 * @param presignedUrl 署名付きURL
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record FileUploadResponse(String fileId,String objectKey,String presignedUrl){}
