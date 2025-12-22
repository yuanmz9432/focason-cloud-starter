package com.focason.platform.config;

import com.focason.core.utility.FsContext;
import com.focason.core.utility.FsUtilityToolkit;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * FsContextFilter
 * <p>
 * This filter intercepts incoming HTTP requests to extract user identity information
 * from custom headers and injects them into the SLF4J Mapped Diagnostic Context (MDC).
 * * Purpose:
 * 1. Enables log traceability by associating user IDs/emails with all log statements in the current thread.
 * 2. Standardizes context extraction across the platform.
 * 3. Prevents context leakage between requests in thread-pooled environments.
 */
@Component
public class FsContextFilter extends OncePerRequestFilter
{
    /**
     * Filters internal requests to populate diagnostic context.
     *
     * @param request The incoming HttpServletRequest.
     * @param response The outgoing HttpServletResponse.
     * @param filterChain The filter chain to proceed with.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs during filtering.
     */
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        @NotNull HttpServletResponse response,
        @NotNull FilterChain filterChain)
        throws ServletException, IOException {

        // Extract user identity headers (typically passed by an API Gateway or Load Balancer)
        String userId = request.getHeader(FsContext.X_USER_ID_HEADER);
        String email = request.getHeader(FsContext.X_USER_EMAIL_HEADER);

        // Populate MDC with extracted values if they exist.
        // These keys can be referenced in logback patterns using %X{key}
        if (userId != null) {
            MDC.put(FsUtilityToolkit.CLAIMS_SUB, userId);
        }

        if (email != null) {
            MDC.put(FsUtilityToolkit.CLAIMS_EMAIL, email);
        }

        try {
            // Proceed to the next filter or the target Controller
            filterChain.doFilter(request, response);
        } finally {
            // ⭐ CRITICAL: Clear the MDC after the request is processed.
            // Since Servlet containers (like Tomcat) reuse threads from a pool,
            // failing to clear the MDC will lead to "Context Leaking," where
            // data from a previous request appears in logs of a subsequent request.
            MDC.clear();
        }
    }
}
