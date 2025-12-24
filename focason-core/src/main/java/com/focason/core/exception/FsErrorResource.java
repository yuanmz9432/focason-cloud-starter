/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.focason.core.utility.FsUtilityToolkit;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Builder;

/**
 * Standardized error response resource.
 * <p>
 * This record represents a unified error response format across the entire Spring Cloud system.
 * It includes error code, message, timestamp, request path, trace ID, and optional details.
 * </p>
 *
 * @param code Error code (e.g., "E400001")
 * @param message Human-readable error message
 * @param timestamp Timestamp when the error occurred (ISO-8601 format)
 * @param path Request path where the error occurred
 * @param traceId Request trace ID for distributed tracing
 * @param details Additional error details (e.g., validation errors)
 */
@Builder(toBuilder=true)@JsonInclude(JsonInclude.Include.NON_NULL)public record FsErrorResource(String code,String message,long timestamp,String path,String traceId,Map<String,Object>details){
/**
 * Creates a simple error resource with only code and message.
 *
 * @param code Error code
 * @param message Error message
 * @return {@link FsErrorResource}
 */
public static FsErrorResource of(String code,String message){return FsErrorResource.builder().code(code).message(message).timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now())).build();}

/**
 * Creates an error resource with validation details.
 *
 * @param code Error code
 * @param message Error message
 * @param validationErrors List of validation errors
 * @return {@link FsErrorResource}
 */
public static FsErrorResource withValidationErrors(String code,String message,List<String>validationErrors){return FsErrorResource.builder().code(code).message(message).timestamp(FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now())).details(Map.of("validationErrors",validationErrors)).build();}}
