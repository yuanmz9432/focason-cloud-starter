// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.focason.core.exception.FsErrorCode;
import com.focason.core.exception.FsErrorResource;
import com.focason.core.exception.FsException;
import com.focason.core.utility.FsExceptionToolKit;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * FeignExceptionHandler
 * <p>
 * Custom error decoder for Feign client calls. This handler intercepts errors from downstream
 * services and attempts to parse standardized error responses (FsErrorResource) from the
 * response body. If parsing fails, it creates a generic error response.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class FeignExceptionHandler implements ErrorDecoder
{
    private static final Logger logger = LoggerFactory.getLogger(FeignExceptionHandler.class);
    private final ObjectMapper objectMapper;

    /**
     * Decodes Feign response errors into appropriate exceptions.
     *
     * @param methodKey Feign method key (e.g., "UserService#getUser(String)")
     * @param response Feign response object
     * @return Decoded exception (FsException if error resource is parsed, FeignException otherwise)
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        logger.warn("Feign error occurred: methodKey={}, status={}, reason={}",
            methodKey, response.status(), response.reason());

        // Try to parse FsErrorResource from response body
        FsErrorResource errorResource = parseErrorResource(response);

        if (errorResource != null) {
            // If we successfully parsed an error resource, wrap it in FsException
            FsErrorCode errorCode = FsErrorCode.of(errorResource.code());
            return new FsException(errorCode, errorResource.message());
        }

        // If parsing failed, create a generic error based on HTTP status
        String message = String.format("Downstream service error: %s %s",
            response.status(), response.reason());
        FsErrorCode errorCode = FsExceptionToolKit.mapHttpStatusToErrorCode(HttpStatus.resolve(response.status()));

        return new FsException(errorCode, message);
    }

    /**
     * Attempts to parse FsErrorResource from Feign response body.
     *
     * @param response Feign response
     * @return Parsed FsErrorResource or null if parsing fails
     */
    private FsErrorResource parseErrorResource(Response response) {
        if (response.body() == null) {
            return null;
        }

        try (InputStream inputStream = response.body().asInputStream()) {
            return FsExceptionToolKit.parseErrorResource(inputStream, objectMapper);
        } catch (IOException e) {
            logger.debug("Failed to parse error resource from Feign response: {}", e.getMessage());
            return null;
        }
    }
}
