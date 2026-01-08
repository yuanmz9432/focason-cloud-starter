/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.resolver;



import com.focason.core.annotation.FsPaginationParam;
import com.focason.core.attribute.FsPagination;
import com.focason.core.exception.FsValidationErrorException;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FsPaginationParamHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver
{

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(FsPagination.class)
            && parameter.hasParameterAnnotation(FsPaginationParam.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
        @NonNull NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {
        FsPaginationParam annotation = parameter.getParameterAnnotation(FsPaginationParam.class);
        if (annotation == null) {
            return null;
        } else {
            int limit;
            try {
                limit = extractIntegerParameter(webRequest, "limit", annotation.defaultLimitValue());
                if (limit > annotation.maxLimitValue()) {
                    throw new FsValidationErrorException("Parameter '%s' must be less than or equal to '%s'.", "limit",
                        annotation.maxLimitValue());
                }
            } catch (NumberFormatException e) {
                throw new FsValidationErrorException("Parameter '%s' must be in the correct number format", "limit");
            }

            int page;
            try {
                page = extractIntegerParameter(webRequest, "page", 1);
                if (page <= 0) {
                    throw new FsValidationErrorException("Parameter '%s' must be greater than or equal to 1.", "page");
                }
            } catch (NumberFormatException e) {
                throw new FsValidationErrorException("Parameter '%s' must be in the correct number format", "page");
            }
            return FsPagination.of(limit, page);
        }
    }

    private int extractIntegerParameter(@NonNull NativeWebRequest webRequest, @NonNull String name, int defaultValue)
        throws NumberFormatException {
        String value = webRequest.getParameter(name);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }
}
