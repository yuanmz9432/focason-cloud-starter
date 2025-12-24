/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.utility;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.focason.core.exception.FsErrorCode;
import com.focason.core.exception.FsErrorResource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Utility class for exception handling and error resource creation.
 * <p>
 * Provides common methods for parsing error responses, extracting HTTP status codes,
 * and creating standardized error resources.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public final class FsExceptionToolKit
{
    private static final Logger logger = LoggerFactory.getLogger(FsExceptionToolKit.class);

    private FsExceptionToolKit() {
        // Utility class
    }

    /**
     * Extracts HTTP status from error code.
     * Error codes follow the format: E{HTTP_STATUS}{SEQUENCE}
     * Example: E400001 -> 400, E500000 -> 500
     *
     * @param code Error code
     * @param defaultStatus Default HTTP status if extraction fails
     * @return HTTP status
     */
    public static HttpStatus extractHttpStatus(String code, HttpStatus defaultStatus) {
        try {
            if (code != null && code.length() >= 4 && code.startsWith("E")) {
                int statusCode = Integer.parseInt(code.substring(1, 4));
                HttpStatus status = HttpStatus.resolve(statusCode);
                return status != null ? status : defaultStatus;
            }
        } catch (NumberFormatException e) {
            logger.warn("Invalid error code format: {}", code);
        }
        return defaultStatus;
    }

    /**
     * Parses FsErrorResource from an input stream (e.g., from Feign response body).
     *
     * @param inputStream Input stream containing JSON error response
     * @param objectMapper ObjectMapper for JSON parsing
     * @return Parsed FsErrorResource or null if parsing fails
     */
    public static FsErrorResource parseErrorResource(InputStream inputStream, ObjectMapper objectMapper) {
        try {
            if (inputStream != null && inputStream.available() > 0) {
                return objectMapper.readValue(inputStream, FsErrorResource.class);
            }
        } catch (IOException e) {
            logger.debug("Failed to parse error resource from stream: {}", e.getMessage());
        }
        return null;
    }

    /**
     * Parses FsErrorResource from a byte array.
     *
     * @param bytes Byte array containing JSON error response
     * @param objectMapper ObjectMapper for JSON parsing
     * @return Parsed FsErrorResource or null if parsing fails
     */
    public static FsErrorResource parseErrorResource(byte[] bytes, ObjectMapper objectMapper) {
        try {
            if (bytes != null && bytes.length > 0) {
                return objectMapper.readValue(bytes, FsErrorResource.class);
            }
        } catch (IOException e) {
            logger.debug("Failed to parse error resource from bytes: {}", e.getMessage());
        }
        return null;
    }

    /**
     * Creates a default error resource with timestamp and optional path/traceId.
     *
     * @param code Error code
     * @param message Error message
     * @param path Request path (optional)
     * @param traceId Trace ID (optional)
     * @return FsErrorResource instance
     */
    public static FsErrorResource createErrorResource(String code, String message, String path, String traceId) {
        return FsErrorResource.builder()
            .code(code)
            .message(message)
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(path)
            .traceId(traceId)
            .build();
    }

    /**
     * Maps HTTP status to appropriate FsErrorCode.
     *
     * @param httpStatus HTTP status
     * @return Corresponding FsErrorCode
     */
    public static FsErrorCode mapHttpStatusToErrorCode(HttpStatus httpStatus) {
        if (httpStatus == null) {
            return FsErrorCode.INTERNAL_SERVER_ERROR;
        }

        return switch (httpStatus.series()) {
            case CLIENT_ERROR -> switch (httpStatus) {
                case UNAUTHORIZED -> FsErrorCode.UNAUTHORIZED;
                case FORBIDDEN -> FsErrorCode.FORBIDDEN;
                case NOT_FOUND -> FsErrorCode.NOT_FOUND;
                case METHOD_NOT_ALLOWED -> FsErrorCode.METHOD_NOT_ALLOWED;
                case CONFLICT -> FsErrorCode.CONFLICT;
                case UNSUPPORTED_MEDIA_TYPE -> FsErrorCode.UNSUPPORTED_MEDIA_TYPE;
                default -> FsErrorCode.BAD_REQUEST;
            };
            default -> FsErrorCode.INTERNAL_SERVER_ERROR;
        };
    }
}
