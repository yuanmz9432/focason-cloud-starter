// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.config;

import com.focason.core.utility.FsContext;
import com.focason.core.utility.FsUtilityToolkit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

/**
 * FsSecurityContextRepository
 * <p>
 * Implements the {@link SecurityContextRepository} contract for stateless environments.
 * This class is primarily responsible for propagating user identification (ID and Email)
 * from HTTP request headers (presumably set by an API Gateway or a preceding authentication filter)
 * into the Mapped Diagnostic Context (MDC) for logging purposes.
 * <p>
 * Since this implementation does not persist or retrieve contexts from a session,
 * {@link #containsContext(HttpServletRequest)} returns false and
 * {@link #saveContext(SecurityContext, HttpServletRequest, HttpServletResponse)} is a no-op.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@AllArgsConstructor(onConstructor = @__({
    @Autowired, @Lazy
}))
public class FsSecurityContextRepository implements SecurityContextRepository
{

    /**
     * Loads the {@link SecurityContext} for the current request.
     * <p>
     * In this stateless implementation, the main action is extracting user claims
     * (ID and Email) from the request headers and populating the MDC for logging.
     * It then returns a new, empty {@link SecurityContextImpl}, assuming the actual
     * authentication details will be populated by a later filter (if required).
     * </p>
     *
     * @param requestResponseHolder Holder for the request and response.
     * @return A new, empty SecurityContext.
     */
    @Override
    @Deprecated // Note: Spring Security has deprecated this specific loadContext signature since 5.7
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        // 1. Retrieve User ID from header and put it into MDC for log correlation
        MDC.put(FsUtilityToolkit.CLAIMS_SUB,
            requestResponseHolder.getRequest().getHeader(FsContext.X_USER_ID_HEADER));

        // 2. Retrieve User Email from header and put it into MDC for log correlation
        MDC.put(FsUtilityToolkit.CLAIMS_EMAIL,
            requestResponseHolder.getRequest().getHeader(FsContext.X_USER_EMAIL_HEADER));

        // 3. Return a new, empty context. Context population must be handled by another filter.
        return new SecurityContextImpl();
    }

    /**
     * Checks if a security context is available for the current request.
     * <p>
     * Returns false as this repository is designed for stateless contexts (no session lookup).
     * </p>
     *
     * @param request The current HttpServletRequest.
     * @return Always returns false.
     */
    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }

    /**
     * Saves the security context.
     * <p>
     * This method is implemented as a no-operation (no-op) because the application uses
     * stateless token-based authentication and does not require persisting the context
     * back to a session or permanent store.
     * </p>
     *
     * @param context The SecurityContext to save.
     * @param request The current HttpServletRequest.
     * @param response The current HttpServletResponse.
     */
    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        // No-op for stateless architecture
    }
}
