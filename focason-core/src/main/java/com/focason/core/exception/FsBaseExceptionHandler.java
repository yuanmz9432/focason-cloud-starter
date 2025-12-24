package com.focason.core.exception;


import com.focason.core.utility.FsUtilityToolkit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * FsBaseExceptionHandler
 * <p>
 * Global exception handler for Spring MVC applications. Handles various types of exceptions
 * and converts them into standardized {@link FsErrorResource} responses.
 * </p>
 *
 * @since 0.0.1
 * @author yuanmz9432
 */
@RestControllerAdvice
public class FsBaseExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(FsBaseExceptionHandler.class);

    /**
     * Handles custom FsException.
     *
     * @param request HTTP request
     * @param fsException Custom exception
     * @return ResponseEntity with error resource
     */
    @ExceptionHandler(FsException.class)
    public ResponseEntity<FsErrorResource> handleFsException(HttpServletRequest request, FsException fsException) {
        String code = fsException.getCode().getValue();
        HttpStatus httpStatus = extractHttpStatus(code);

        logger.warn("FsException occurred: code={}, message={}, path={}",
            code, fsException.getMessage(), request.getRequestURI(), fsException);

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(code)
            .message(fsException.getMessage())
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(httpStatus).body(errorResource);
    }

    /**
     * Handles validation errors from @Valid annotation.
     *
     * @param request HTTP request
     * @param e MethodArgumentNotValidException
     * @return ResponseEntity with validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FsErrorResource> handleValidationException(
        HttpServletRequest request,
        MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> validationErrors = new ArrayList<>();

        if (bindingResult.hasFieldErrors()) {
            validationErrors.addAll(bindingResult.getFieldErrors().stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .toList());
        }

        if (bindingResult.hasGlobalErrors()) {
            validationErrors.addAll(bindingResult.getGlobalErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList());
        }

        String message = validationErrors.isEmpty()
            ? "Validation failed"
            : String.join("; ", validationErrors);

        logger.warn("Validation error: path={}, errors={}", request.getRequestURI(), validationErrors);

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.VALIDATION_ERROR.getValue())
            .message(message)
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .details(Map.of("validationErrors", validationErrors))
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResource);
    }

    /**
     * Handles constraint violation exceptions (e.g., @Validated on method parameters).
     *
     * @param request HTTP request
     * @param e ConstraintViolationException
     * @return ResponseEntity with constraint violations
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<FsErrorResource> handleConstraintViolationException(
        HttpServletRequest request,
        ConstraintViolationException e) {
        List<String> violations = e.getConstraintViolations().stream()
            .map(violation -> String.format("%s: %s",
                violation.getPropertyPath().toString(),
                violation.getMessage()))
            .collect(Collectors.toList());

        logger.warn("Constraint violation: path={}, violations={}", request.getRequestURI(), violations);

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.VALIDATION_ERROR.getValue())
            .message("Constraint validation failed: " + String.join("; ", violations))
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .details(Map.of("constraintViolations", violations))
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResource);
    }

    /**
     * Handles HTTP method not supported exceptions.
     *
     * @param request HTTP request
     * @param e HttpRequestMethodNotSupportedException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<FsErrorResource> handleMethodNotSupportedException(
        HttpServletRequest request,
        HttpRequestMethodNotSupportedException e) {
        logger.warn("Method not supported: method={}, path={}", e.getMethod(), request.getRequestURI());

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.METHOD_NOT_ALLOWED.getValue())
            .message(String.format("HTTP method '%s' is not supported for this endpoint", e.getMethod()))
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResource);
    }

    /**
     * Handles HTTP media type not supported exceptions.
     *
     * @param request HTTP request
     * @param e HttpMediaTypeNotSupportedException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<FsErrorResource> handleMediaTypeNotSupportedException(
        HttpServletRequest request,
        HttpMediaTypeNotSupportedException e) {
        logger.warn("Media type not supported: contentType={}, path={}", e.getContentType(), request.getRequestURI());

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.UNSUPPORTED_MEDIA_TYPE.getValue())
            .message(String.format("Content type '%s' is not supported", e.getContentType()))
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResource);
    }

    /**
     * Handles missing request parameter exceptions.
     *
     * @param request HTTP request
     * @param e MissingServletRequestParameterException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<FsErrorResource> handleMissingParameterException(
        HttpServletRequest request,
        MissingServletRequestParameterException e) {
        logger.warn("Missing parameter: parameter={}, path={}", e.getParameterName(), request.getRequestURI());

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.BAD_REQUEST.getValue())
            .message(String.format("Required parameter '%s' is missing", e.getParameterName()))
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResource);
    }

    /**
     * Handles method argument type mismatch exceptions.
     *
     * @param request HTTP request
     * @param e MethodArgumentTypeMismatchException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<FsErrorResource> handleTypeMismatchException(
        HttpServletRequest request,
        MethodArgumentTypeMismatchException e) {
        logger.warn("Type mismatch: parameter={}, value={}, path={}",
            e.getName(), e.getValue(), request.getRequestURI());

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.BAD_REQUEST.getValue())
            .message(String.format("Parameter '%s' has invalid value '%s'", e.getName(), e.getValue()))
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResource);
    }

    /**
     * Handles HTTP message not readable exceptions (e.g., malformed JSON).
     *
     * @param request HTTP request
     * @param e HttpMessageNotReadableException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<FsErrorResource> handleMessageNotReadableException(
        HttpServletRequest request,
        HttpMessageNotReadableException e) {
        logger.warn("Message not readable: path={}, error={}", request.getRequestURI(), e.getMessage());

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.BAD_REQUEST.getValue())
            .message("Request body is malformed or invalid")
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResource);
    }

    /**
     * Handles data integrity violation exceptions (e.g., foreign key constraints).
     *
     * @param request HTTP request
     * @param e DataIntegrityViolationException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<FsErrorResource> handleDataIntegrityViolationException(
        HttpServletRequest request,
        DataIntegrityViolationException e) {
        logger.error("Data integrity violation: path={}", request.getRequestURI(), e);

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.DATA_INTEGRITY_VIOLATION.getValue())
            .message("Data integrity constraint violation")
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResource);
    }

    /**
     * Handles duplicate key exceptions.
     *
     * @param request HTTP request
     * @param e DuplicateKeyException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<FsErrorResource> handleDuplicateKeyException(
        HttpServletRequest request,
        DuplicateKeyException e) {
        logger.warn("Duplicate key: path={}", request.getRequestURI(), e);

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.DUPLICATE_ENTRY.getValue())
            .message("Duplicate entry detected")
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResource);
    }

    /**
     * Handles generic data access exceptions.
     *
     * @param request HTTP request
     * @param e DataAccessException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<FsErrorResource> handleDataAccessException(
        HttpServletRequest request,
        DataAccessException e) {
        logger.error("Data access error: path={}", request.getRequestURI(), e);

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.INTERNAL_SERVER_ERROR.getValue())
            .message("Database operation failed")
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResource);
    }

    /**
     * Handles no handler found exceptions (404).
     *
     * @param request HTTP request
     * @param e NoHandlerFoundException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<FsErrorResource> handleNoHandlerFoundException(
        HttpServletRequest request,
        NoHandlerFoundException e) {
        logger.warn("No handler found: method={}, path={}", e.getHttpMethod(), e.getRequestURL());

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.NOT_FOUND.getValue())
            .message(String.format("No handler found for %s %s", e.getHttpMethod(), e.getRequestURL()))
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResource);
    }

    /**
     * Handles all other unhandled exceptions.
     *
     * @param request HTTP request
     * @param e Exception
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<FsErrorResource> handleGenericException(
        HttpServletRequest request,
        Exception e) {
        logger.error("Unhandled exception: path={}", request.getRequestURI(), e);

        // In production, don't expose internal error messages
        String message = "An internal error occurred";
        // In development, you might want to include the exception message
        // String message = e.getMessage() != null ? e.getMessage() : "An internal error occurred";

        FsErrorResource errorResource = FsErrorResource.builder()
            .code(FsErrorCode.INTERNAL_SERVER_ERROR.getValue())
            .message(message)
            .timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now()))
            .path(request.getRequestURI())
            .traceId(extractTraceId(request))
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResource);
    }

    /**
     * Extracts HTTP status from error code.
     * Error codes follow the format: E{HTTP_STATUS}{SEQUENCE}
     * Example: E400001 -> 400, E500000 -> 500
     *
     * @param code Error code
     * @return HTTP status
     */
    private HttpStatus extractHttpStatus(String code) {
        try {
            if (code != null && code.length() >= 4 && code.startsWith("E")) {
                int statusCode = Integer.parseInt(code.substring(1, 4));
                HttpStatus status = HttpStatus.resolve(statusCode);
                return status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (NumberFormatException e) {
            logger.warn("Invalid error code format: {}", code);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Extracts trace ID from request headers.
     * Supports common tracing headers: X-Trace-Id, X-Request-Id, X-Correlation-Id
     *
     * @param request HTTP request
     * @return Trace ID or null if not found
     */
    private String extractTraceId(HttpServletRequest request) {
        String traceId = request.getHeader("X-Trace-Id");
        if (traceId == null) {
            traceId = request.getHeader("X-Request-Id");
        }
        if (traceId == null) {
            traceId = request.getHeader("X-Correlation-Id");
        }
        return traceId;
    }
}
