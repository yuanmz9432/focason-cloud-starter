// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway.handler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.focason.core.exception.FsErrorCode;
import com.focason.core.exception.FsErrorResource;
import com.focason.core.exception.FsException;
import com.focason.core.utility.FsExceptionToolKit;
import com.focason.core.utility.FsUtilityToolkit;
import feign.FeignException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GatewayExceptionHandler
 * <p>
 * Global exception handler for the Spring Cloud Gateway (Reactive Stack).
 * <p>
 * This component captures exceptions thrown during the gateway filtering and routing process,
 * converts them into a standardized JSON error response format (FsErrorResource), and writes
 * the response back to the client. It handles custom {@link FsException} types,
 * {@link FeignException} (downstream service communication errors), and generic errors.
 * </p>
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
 */
@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GatewayExceptionHandler implements ErrorWebExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(GatewayExceptionHandler.class);
    private final ObjectMapper objectMapper;

    /**
     * Handles exceptions occurring within the Gateway processing pipeline.
     *
     * @param exchange the current server exchange.
     * @param ex the exception to handle.
     * @return {@link Mono<Void>} completing when the response has been written.
     */
    @Override
    @NonNull
    public Mono<Void> handle(ServerWebExchange exchange, @NonNull Throwable ex) {
        logger.error("Gateway Exception Handler caught exception: path={}, error={}",
            exchange.getRequest().getPath(), ex.getMessage());

        final ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Determine error details (code, message, HTTP status) based on the exception type
        ErrorDetail errorDetail = resolveErrorDetail(ex, exchange);

        response.setStatusCode(errorDetail.httpStatus());

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                // Return the standardized error response
                return bufferFactory.wrap(
                    objectMapper.writeValueAsBytes(errorDetail.errorResource()));
            } catch (JsonProcessingException e) {
                logger.error("Error serializing response body: {}", e.getMessage());
                // Fallback to a simple error response
                try {
                    FsErrorResource fallback = FsErrorResource.of(
                        FsErrorCode.INTERNAL_SERVER_ERROR.getValue(),
                        "An error occurred while processing your request");
                    return bufferFactory.wrap(objectMapper.writeValueAsBytes(fallback));
                } catch (JsonProcessingException ex2) {
                    logger.error("Failed to create fallback error response");
                    return bufferFactory.wrap(new byte[0]);
                }
            }
        }));
    }

    /**
     * Internal helper record to hold resolved error details.
     */
    private record ErrorDetail(FsErrorResource errorResource, HttpStatus httpStatus) {}

    /**
     * Resolves the appropriate error code, message, and HTTP status based on the exception.
     *
     * @param ex The thrown exception.
     * @param exchange The server web exchange (for extracting request path and trace ID).
     * @return {@link ErrorDetail} containing standardized error information.
     */
    private ErrorDetail resolveErrorDetail(@NonNull Throwable ex, ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().value();
        String traceId = extractTraceId(exchange);

        // Handle custom FsException
        if (ex instanceof FsException fsException) {
            String code = fsException.getCode().getValue();
            HttpStatus httpStatus = FsExceptionToolKit.extractHttpStatus(code, HttpStatus.INTERNAL_SERVER_ERROR);

            FsErrorResource errorResource = FsExceptionToolKit.createErrorResource(
                code, fsException.getMessage(), path, traceId);

            return new ErrorDetail(errorResource, httpStatus);
        }

        // Handle FeignException (downstream service errors)
        if (ex instanceof FeignException feignEx) {
            return handleFeignException(feignEx, path, traceId);
        }

        // Handle ResponseStatusException (Spring WebFlux)
        if (ex instanceof ResponseStatusException statusEx) {
            HttpStatus httpStatus = HttpStatus.resolve(statusEx.getStatusCode().value());
            if (httpStatus == null) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            FsErrorCode errorCode = FsExceptionToolKit.mapHttpStatusToErrorCode(httpStatus);
            FsErrorResource errorResource = FsExceptionToolKit.createErrorResource(
                errorCode.getValue(),
                statusEx.getReason() != null ? statusEx.getReason() : httpStatus.getReasonPhrase(),
                path,
                traceId);

            return new ErrorDetail(errorResource, httpStatus);
        }

        // Default handling for unexpected or generic exceptions
        logger.error("Unexpected exception occurred in Gateway: ", ex);

        FsErrorResource errorResource = FsExceptionToolKit.createErrorResource(
            FsErrorCode.INTERNAL_SERVER_ERROR.getValue(),
            "An internal error occurred",
            path,
            traceId);

        return new ErrorDetail(errorResource, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles FeignException by attempting to parse error resource from response body.
     *
     * @param feignEx Feign exception
     * @param path Request path
     * @param traceId Trace ID
     * @return ErrorDetail with parsed or default error information
     */
    private ErrorDetail handleFeignException(FeignException feignEx, String path, String traceId) {
        // Try to parse FsErrorResource from Feign response body
        FsErrorResource errorResource = null;
        if (feignEx.contentUTF8() != null && !feignEx.contentUTF8().isEmpty()) {
            try {
                errorResource = FsExceptionToolKit.parseErrorResource(
                    feignEx.contentUTF8().getBytes(), objectMapper);
            } catch (Exception e) {
                logger.debug("Failed to parse error resource from Feign exception: {}", e.getMessage());
            }
        }

        // If parsing failed, create a generic error
        if (errorResource == null) {
            HttpStatus httpStatus = HttpStatus.resolve(feignEx.status());
            if (httpStatus == null) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            FsErrorCode errorCode = FsExceptionToolKit.mapHttpStatusToErrorCode(httpStatus);
            errorResource = FsExceptionToolKit.createErrorResource(
                errorCode.getValue(),
                "Downstream service error: " + (feignEx.getMessage() != null ? feignEx.getMessage() : "Unknown error"),
                path,
                traceId);
        } else {
            // Update path and traceId if error resource was parsed
            errorResource = errorResource.toBuilder()
                .path(path)
                .traceId(traceId)
                .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
                .build();
        }

        HttpStatus httpStatus = FsExceptionToolKit.extractHttpStatus(
            errorResource.code(),
            HttpStatus.resolve(feignEx.status()) != null
                ? HttpStatus.resolve(feignEx.status())
                : HttpStatus.INTERNAL_SERVER_ERROR);

        return new ErrorDetail(errorResource, httpStatus);
    }

    /**
     * Extracts trace ID from request headers.
     * Supports common tracing headers: X-Trace-Id, X-Request-Id, X-Correlation-Id
     *
     * @param exchange Server web exchange
     * @return Trace ID or null if not found
     */
    private String extractTraceId(ServerWebExchange exchange) {
        String traceId = exchange.getRequest().getHeaders().getFirst("X-Trace-Id");
        if (traceId == null) {
            traceId = exchange.getRequest().getHeaders().getFirst("X-Request-Id");
        }
        if (traceId == null) {
            traceId = exchange.getRequest().getHeaders().getFirst("X-Correlation-Id");
        }
        return traceId;
    }
}
