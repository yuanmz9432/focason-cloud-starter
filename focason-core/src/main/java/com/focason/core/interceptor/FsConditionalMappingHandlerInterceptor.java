// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.interceptor;

import com.focason.core.annotation.FsConditionalMappingOnProperty;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * FsConditionalMappingHandlerInterceptor
 * <p>
 * An implementation of Spring's {@link HandlerInterceptor} that enforces conditional
 * access to controller mappings based on environment properties.
 * <p>
 * If a controller class or method is annotated with {@link FsConditionalMappingOnProperty},
 * this interceptor checks if the specified environment property value matches the
 * expected value. If the condition is not met (i.e., the mapping is disabled), it
 * throws a {@link NoHandlerFoundException}, effectively hiding the endpoint from external access.
 * </p>
 *
 * @param environment The Spring environment, used to fetch property values.
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record FsConditionalMappingHandlerInterceptor(Environment environment)implements HandlerInterceptor{

/**
 * Pre-handles the request before the handler is executed.
 * <p>
 * Checks both method-level and class-level annotations to determine if the mapping
 * should be disabled based on environment properties.
 * </p>
 *
 * @param request current HTTP request
 * @param response current HTTP response
 * @param handler chosen handler to execute, potentially a HandlerMethod
 * @return true to proceed with the execution chain, false otherwise (by throwing an exception).
 * @throws NoHandlerFoundException if the mapping is intentionally disabled by the property condition.
 */
@Override public boolean preHandle(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response,@NotNull Object handler)throws NoHandlerFoundException{
// 1. Check if the handler is a controller method
if(!(handler instanceof HandlerMethod method)){return true;}

// 2. Check method-level annotation
FsConditionalMappingOnProperty methodAnnotation=method.getMethodAnnotation(FsConditionalMappingOnProperty.class);if(this.isMappingDisabled(methodAnnotation)){throw this.getNoHandlerFoundException(request);}

// 3. Check class-level annotation
FsConditionalMappingOnProperty classAnnotation=method.getBeanType().getAnnotation(FsConditionalMappingOnProperty.class);if(this.isMappingDisabled(classAnnotation)){throw this.getNoHandlerFoundException(request);}

return true;}

/**
 * Determines if the mapping should be disabled based on the property annotation.
 * <p>
 * Logic checks for the presence of the property and matches its value against the required 'havingValue'.
 * </p>
 *
 * @param annotation The method or class annotation.
 * @return true if the mapping should be disabled (condition not met), false otherwise.
 */
private boolean isMappingDisabled(FsConditionalMappingOnProperty annotation){if(annotation!=null){String propValue=this.environment.getProperty(annotation.name());

// If the property value is not found/empty
if(!StringUtils.hasText(propValue)){
// If missing, defer to the annotation's 'matchIfMissing' setting (usually false to enable by default)
return annotation.matchIfMissing();}else{String havingValue=annotation.havingValue();
// The mapping is disabled if:
// a) The required value is present AND the property value does NOT match OR
// b) The required value matches the actual property value. (This part is unusual but follows the provided logic
// structure)
return StringUtils.hasText(havingValue)||havingValue.equals(propValue);}}else{
// No annotation, so mapping is not disabled
return false;}}

/**
 * Constructs a {@link NoHandlerFoundException} with all necessary request details
 * (method, URI, and headers) to correctly mimic a missing handler.
 *
 * @param request The current HttpServletRequest.
 * @return A NoHandlerFoundException instance.
 */
private NoHandlerFoundException getNoHandlerFoundException(HttpServletRequest request){
// Collect all request headers into a MultiValueMap for the HttpHeaders constructor
Map<String,List<String>>headerMap=Collections.list(request.getHeaderNames()).stream().collect(Collectors.toMap(Function.identity(),(name)->Collections.list(request.getHeaders(name))));

return new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),new HttpHeaders(new LinkedMultiValueMap<>(headerMap)));}}
