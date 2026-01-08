// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.config;

import com.focason.core.utility.FsUtilityToolkit;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * WebSocketAuthInterceptor
 * <p>
 * Implements both {@link ChannelInterceptor} and {@link HandshakeInterceptor} to
 * manage authentication during the WebSocket connection establishment phase.
 * <p>
 * This interceptor is responsible for extracting the access token from the URL parameters
 * during the initial handshake, extracting the user ID from the token, and setting
 * the user ID in the WebSocket session attributes for later use (e.g., creating a Principal).
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor, HandshakeInterceptor
{
    final Logger logger = LoggerFactory.getLogger(WebSocketAuthInterceptor.class);

    /**
     * Called before the WebSocket handshake is established.
     * <p>
     * Performs authentication logic: extracts the access token from the URL query
     * parameter and places the derived user ID into the WebSocket session attributes.
     * </p>
     *
     * @param request The current HTTP request.
     * @param response The current HTTP response.
     * @param wsHandler The WebSocketHandler that will handle the connection.
     * @param attributes Attributes shared between the handshake and WebSocket session.
     * @return true to continue the handshake, false to refuse the connection.
     */
    @Override
    public boolean beforeHandshake(
        @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
        @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {

        // Ensure we are working with a standard servlet request
        if (!(request instanceof ServletServerHttpRequest servletRequest)) {
            logger.warn("Request is not a ServletServerHttpRequest, skipping token extraction.");
            return true;
        }

        // 1. Extract the access token from the URL query parameter (access_token)
        String accessToken = servletRequest.getServletRequest().getParameter("access_token");

        if (accessToken == null) {
            logger.warn("WebSocket handshake failed: 'access_token' parameter is missing.");
            // Optionally, set the response status to 401/403 before returning false
            // response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }

        // 2. Extract the user's unique ID (UID) from the token payload
        final var uid = FsUtilityToolkit.extractUserIdFromToken(accessToken);

        if (uid == null) {
            logger.warn("WebSocket handshake failed: Could not extract valid UID from access token.");
            return false;
        }

        // 3. Store the user ID in the attributes map. This ID will be used later
        // by a custom PrincipalFactory to create the user's Principal object in the session.
        attributes.put("userId", uid);
        logger.info("WebSocket handshake successful for userId: {}", uid);

        return true;
    }

    /**
     * Called after the WebSocket handshake has been established (or failed).
     * This method is a no-operation (no-op) in this implementation.
     *
     * @param request The current HTTP request.
     * @param response The current HTTP response.
     * @param wsHandler The WebSocketHandler.
     * @param ex Exception if the handshake failed, or null otherwise.
     */
    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
        @NonNull WebSocketHandler wsHandler, @Nullable Exception ex) {
        // Implementation specific cleanup or logging can go here, but currently a no-op.
    }

    // ChannelInterceptor methods are generally used for intercepting STOMP messages,
    // which are not implemented here but required by the interface.
    // We omit them for brevity since the primary focus is on the Handshake.
}
