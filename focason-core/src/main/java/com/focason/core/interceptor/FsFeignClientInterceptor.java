// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.interceptor;

import feign.RequestInterceptor;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * FsFeignClientInterceptor
 * <p>
 * A configuration class that provides a Feign RequestInterceptor bean.
 * This interceptor is responsible for propagating the security context (e.g., the
 * Authorization header) from the incoming HTTP request to outgoing Feign client calls,
 * ensuring that authentication is maintained across microservice boundaries.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class FsFeignClientInterceptor
{
    /**
     * Creates and configures a {@link RequestInterceptor} bean for Feign clients.
     * <p>
     * This interceptor automatically copies the 'Authorization' header from the
     * current incoming HTTP request (managed by Spring's RequestContextHolder)
     * and sets it on the outgoing Feign request template.
     * </p>
     *
     * @return A Feign RequestInterceptor instance.
     */
    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return template -> {
            // 1. Attempt to retrieve the current request attributes bound to the thread
            ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                // 2. Get the actual HttpServletRequest object
                HttpServletRequest request = attributes.getRequest();

                // 3. Extract the Authorization header from the incoming request
                String authorization = request.getHeader("Authorization");

                // 4. If the header exists, propagate it to the outgoing Feign request
                if (authorization != null) {
                    template.header("Authorization", authorization);
                }
            }
        };
    }
}
