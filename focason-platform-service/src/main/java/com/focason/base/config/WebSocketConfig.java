// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.base.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * WebSocketConfig
 * <p>
 * Primary configuration class for setting up STOMP over WebSocket using Spring's
 * message broker capabilities. It registers endpoints, configures the message broker,
 * and integrates the custom {@link WebSocketAuthInterceptor} for authentication.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker // Enables WebSocket message handling, backed by a message broker.
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
{
    /**
     * Interceptor used during the WebSocket handshake and for message processing
     * (as a ChannelInterceptor).
     */
    private final WebSocketAuthInterceptor authInterceptor;

    /**
     * Custom handler used to determine the user Principal during the handshake,
     * typically utilizing the user ID extracted by the {@link WebSocketAuthInterceptor}.
     */
    private final FsPrincipalHandshakeHandler fsPrincipalHandshakeHandler;

    /**
     * Configures interceptors for the client inbound channel.
     * <p>
     * Intercepts messages coming *from* the client before they reach the controller.
     * </p>
     *
     * @param registration The channel registration builder.
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // Use the same interceptor to handle per-message authorization or processing
        registration.interceptors(authInterceptor);
    }

    /**
     * Registers STOMP endpoints, mapping a URL to a WebSocket transport.
     *
     * @param registry The registry to register STOMP endpoints.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp") // Define the HTTP path for WebSocket connection (STOMP over SockJS endpoint)
            .setHandshakeHandler(fsPrincipalHandshakeHandler) // Use custom handler for Principal creation
            .addInterceptors(authInterceptor) // Add handshake interceptor for authentication logic
            .setAllowedOriginPatterns("*") // Allow connections from any origin (Security Note: Consider restricting
                                           // this in production)
            .withSockJS(); // Enable SockJS fallback options for browsers that don't support native WebSocket
    }

    /**
     * Configures the message broker, defining prefixes for destinations.
     *
     * @param registry The message broker registry.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker to handle subscriptions
        registry.enableSimpleBroker("/topic", "/queue");
        // /topic: for broadcasting messages (many-to-many)
        // /queue: for point-to-point and user-specific messages (one-to-one)

        // Defines the prefix for destinations mapped to @MessageMapping annotated methods in controllers
        registry.setApplicationDestinationPrefixes("/app");

        // Defines the prefix used to designate user-specific queues (e.g., /user/queue/notifications)
        registry.setUserDestinationPrefix("/user");
    }

    /**
     * Configures WebSocket transport options, mainly focusing on message size and timeouts.
     *
     * @param registration The registration builder for WebSocket transport settings.
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(128 * 1024); // Set the maximum size for incoming STOMP messages (128KB)
        registration.setSendTimeLimit(20 * 1000); // Set the maximum time allowed to send a message (20 seconds)
        registration.setSendBufferSizeLimit(512 * 1024); // Set the maximum buffer size for outbound messages (512KB)
    }
}
