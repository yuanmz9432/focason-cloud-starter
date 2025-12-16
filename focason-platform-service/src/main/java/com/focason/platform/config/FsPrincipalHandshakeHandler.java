// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.config;

import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * FsPrincipalHandshakeHandler
 * <p>
 * Custom implementation of {@link DefaultHandshakeHandler} used to resolve the user's
 * identity (Principal) during the initial WebSocket handshake phase.
 *
 * <p>
 * This component is essential for securing WebSocket communication, ensuring that
 * the user ID is correctly associated with the WebSocket session, enabling message
 * routing and authorization checks in downstream services.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.web.socket.server.support.DefaultHandshakeHandler
 * @see java.security.Principal
 */
@Component
public class FsPrincipalHandshakeHandler extends DefaultHandshakeHandler
{

    /**
     * Determines the user Principal for the WebSocket session.
     *
     * <p>
     * It extracts the {@code userId} attribute, which is expected to be populated
     * during the handshake interceptor phase (e.g., from an authentication token in the request headers),
     * and wraps it in a {@link UserPrincipal}.
     * </p>
     *
     * @param request The current ServerHttpRequest, providing access to headers and request details.
     * @param wsHandler The WebSocketHandler that will handle the connection.
     * @param attributes Attributes accumulated during the handshake process.
     * @return A {@link Principal} representing the authenticated user, or {@code null} if authentication fails.
     */
    @Override
    protected Principal determineUser(@NotNull ServerHttpRequest request, @NotNull WebSocketHandler wsHandler,
        Map<String, Object> attributes) {
        String userId = (String) attributes.get("userId");
        // We use UserPrincipal for compatibility and simple representation of the user ID.
        return new UserPrincipal(userId);
    }
}
