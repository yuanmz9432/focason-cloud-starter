package com.focason.core.response;

/**
 * FileDownloadResponse
 *
 * @param presignedUrl 署名付きURL
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record FileDownloadResponse(String presignedUrl){}
