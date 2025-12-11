// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway.handler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.focason.core.exception.FsErrorCode;
import com.focason.core.exception.FsErrorResource;
import com.focason.core.exception.FsException;
import feign.FeignException;
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
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GatewayExceptionHandler
 * <p>
 * Global exception handler for the Spring Cloud Gateway (Reactive Stack).
 *
 * <p>
 * This component captures exceptions thrown during the gateway filtering and routing process,
 * converts them into a standardized JSON error response format (FsErrorResource), and writes
 * the response back to the client. It handles custom {@link FsException} types,
 * {@link FeignException} (downstream service communication errors), and generic errors.
 * </p>
 *
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
    final Logger logger = LoggerFactory.getLogger(GatewayExceptionHandler.class);
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
        logger.error("Gateway Exception Handler caught exception: {}", ex.getMessage(), ex);
        final ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Determine error details (code, message, HTTP status) based on the exception type
        ErrorDetail errorDetail = resolveErrorDetail(ex);

        response.setStatusCode(errorDetail.httpStatus);

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                // Return the standardized error response
                return bufferFactory.wrap(
                    objectMapper.writeValueAsBytes(
                        FsErrorResource.builder()
                            .code(errorDetail.code)
                            .message(errorDetail.message)
                            .build()));
            } catch (JsonProcessingException e) {
                logger.error("Error serializing response body: {}", e.getMessage());
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

    /**
     * Internal helper class to hold resolved error details.
     */
    private record ErrorDetail(String code, String message, HttpStatus httpStatus) {}

    /**
     * Resolves the appropriate error code, message, and HTTP status based on the exception.
     *
     * @param ex The thrown exception.
     * @return {@link ErrorDetail} containing standardized error information.
     */
    private ErrorDetail resolveErrorDetail(@NonNull Throwable ex) {
        if (ex instanceof FsException fsException) {
            String code = fsException.getCode().getValue();
            // Assuming FsErrorCode uses a format like "E401001", where 401 is the HTTP status
            try {
                int httpStatusCode = Integer.parseInt(code.substring(1, 4));
                return new ErrorDetail(code, fsException.getMessage(), HttpStatus.valueOf(httpStatusCode));
            } catch (Exception e) {
                logger.warn("FsException code format is invalid: {}. Falling back to default 500.", code);
                return new ErrorDetail(code, fsException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (ex instanceof FeignException feignEx) {
            // For Feign exceptions, typically the downstream service has returned an error.
            // We propagate the HTTP status, and use a generic Feign client error code.
            // Note: If the Feign response body contains FsErrorResource, that should ideally be parsed here.
            // For now, we use a generic Gateway code for Feign issues.
            String gatewayFeignErrorCode = "G500001"; // Generic Gateway Feign Client Error
            HttpStatus status = HttpStatus.resolve(feignEx.status());

            return new ErrorDetail(
                gatewayFeignErrorCode,
                "Downstream service error: " + feignEx.getMessage(),
                status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Default handling for unexpected or generic exceptions
        logger.error("Unexpected exception occurred in Gateway: ", ex);
        return new ErrorDetail(
            FsErrorCode.INTERNAL_SERVER_ERROR.getValue(),
            FsErrorCode.INTERNAL_SERVER_ERROR.name(),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
