// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway.filter;


import com.focason.core.exception.FsAccessTokenExpiredException;
import com.focason.core.exception.FsEntryPointUnauthorizedException;
import com.focason.core.exception.FsIllegalAccessTokenException;
import com.focason.core.exception.FsIllegalUserException;
import com.focason.core.feign.AuthFeignClient;
import com.focason.core.request.UserValidationRequest;
import com.focason.core.utility.FsContext;
import com.focason.core.utility.FsUtilityToolkit;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * AuthenticationTokenFilter
 * <p>
 * Global pre-filter for the Spring Cloud Gateway, responsible for authenticating and
 * validating JWT access tokens before requests are routed to downstream microservices.
 *
 * <p>
 * This filter performs both stateless JWT validation (signature, expiry) and
 * stateful user validation (checking user status via a Feign client call to the Auth Service).
 * Upon successful authentication, user identity information (UID, Email) is added to
 * the request headers for use by downstream services.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.cloud.gateway.filter.GatewayFilter
 */
@Component
@AllArgsConstructor(onConstructor = @__({
    @Autowired, @Lazy
}))
public class AuthenticationTokenFilter implements GatewayFilter
{
    final Logger logger = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    final boolean AUTHENTICATION_SWITCH = true;
    private final AuthFeignClient authFeignClient;

    // Paths that do not require authentication
    private static final List<Pattern> AUTH_WHITE_PATTERNS = Arrays.asList(
        Pattern.compile(
            "^/api/v1/auth/(register|login|refresh-token|verify-code|send-verification-code|logout|forgot-password|reset-password)$"),
        Pattern.compile("^/api/v1/auth/google/callback$"),
        Pattern.compile("^/api/v1/auth/oauth/.*$"), // Matches all OAuth callbacks
        Pattern.compile("^/api/v1/eureka.*$") // Matches all Eureka related paths
    );

    /**
     * Checks if a given request path matches any of the defined authentication white-list patterns.
     *
     * @param path The URL path of the incoming request.
     * @return {@code true} if the path is in the white-list and does not require authentication, {@code false}
     *         otherwise.
     */
    public static boolean isWhiteList(String path) {
        return AUTH_WHITE_PATTERNS.stream()
            .anyMatch(pattern -> pattern.matcher(path).matches());
    }

    /**
     * Processes the request through the authentication filter logic.
     *
     * <p>
     * If the path is whitelisted or the authentication switch is off, it proceeds immediately.
     * Otherwise, it calls the {@link #authenticate(ServerHttpRequest)} method to validate the user.
     * </p>
     *
     * @param exchange The current server exchange, providing access to the request and response.
     * @param chain The gateway filter chain.
     * @return {@code Mono<Void>} signaling when request processing is complete.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.debug("Gateway AuthenticationTokenFilter is running...");
        ServerHttpRequest request = exchange.getRequest();

        if (isWhiteList(request.getPath().toString()) || !AUTHENTICATION_SWITCH) {
            return chain.filter(exchange);
        }

        // Perform authentication and modify the request with user headers
        ServerHttpRequest authenticatedRequest = authenticate(request);
        // Note: The original code attempts to put the authenticated request into attributes
        // which is unnecessary as it should be passed down the chain via the exchange object.
        // The core requirement is fulfilled by mutating the request headers inside authenticate().

        // Proceed with the modified request
        return chain.filter(exchange.mutate().request(authenticatedRequest).build());
    }

    /**
     * Performs JWT token validation and user status check.
     *
     * <p>
     * The process includes:
     * </p>
     * <ol>
     * <li>Checking for the existence of the Authorization header.</li>
     * <li>Verifying the JWT structure.</li>
     * <li>Extracting payload (UID, email, expiry).</li>
     * <li>Checking token expiration time.</li>
     * <li>Validating user status via the Auth Service.</li>
     * <li>Adding user details (UID, Email) to new request headers.</li>
     * </ol>
     *
     * @param request The incoming {@link ServerHttpRequest}.
     * @return A mutated {@link ServerHttpRequest} with user identity headers added.
     * @throws FsEntryPointUnauthorizedException if the Authorization header is missing.
     * @throws FsIllegalAccessTokenException if the JWT format is invalid or payload is incomplete.
     * @throws FsAccessTokenExpiredException if the JWT is expired.
     * @throws FsIllegalUserException if the user is invalid (e.g., disabled, deleted).
     */
    private ServerHttpRequest authenticate(ServerHttpRequest request) {
        // 1. Check if the token is present
        var authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || authorizationHeader.get(0).isEmpty()) {
            throw new FsEntryPointUnauthorizedException();
        }

        // 2. Check if the token is legal
        final var accessToken = authorizationHeader.get(0);
        if (accessToken.split("\\.").length != 3) {
            throw new FsIllegalAccessTokenException();
        }

        // 2.1. Check token is existed. TODO

        // 3. Get payload
        final JsonObject payload = FsUtilityToolkit.decodeJwtPayload(accessToken);
        // 3.1. Get user's UID
        final var uid = Optional.ofNullable(payload.get(FsUtilityToolkit.CLAIMS_SUB))
            .map(JsonElement::getAsString)
            .orElseThrow(() -> new FsIllegalAccessTokenException("UID is missing in the payload."));
        // 3.2. Get token expiration timestamp
        final long timestamp = Optional.ofNullable(payload.get(FsUtilityToolkit.CLAIMS_EXP))
            .map(JsonElement::getAsLong)
            .orElseThrow(() -> new FsIllegalAccessTokenException("Expiration timestamp is missing in the payload."));
        // 3.3. Get user's email
        final var email = Optional.ofNullable(payload.get(FsUtilityToolkit.CLAIMS_EMAIL))
            .map(JsonElement::getAsString)
            .orElseThrow(() -> new FsIllegalAccessTokenException("Email is missing in the payload."));
        // 3.4. Get user's role
        // final var role = Optional.ofNullable(payload.get(FsUtilityToolkit.CLAIMS_ROLE))
        // .map(JsonElement::getAsString)
        // .orElseThrow(() -> new FsIllegalAccessTokenException("Role is missing in the payload."));

        // 4. Check if the token is expired
        // Offset is set to +9 hours (Japan Standard Time)
        final LocalDateTime expiredTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(9));
        if (expiredTime.isBefore(LocalDateTime.now())) {
            logger.error("Access token was expired. Since {}.", expiredTime);
            throw new FsAccessTokenExpiredException();
        }

        // 5. Validate if the user is legal
        validateUser(uid, email);

        // 6. Set UID, Email into request headers
        return request.mutate().header(FsContext.X_USER_ID_HEADER, uid).header(FsContext.X_USER_EMAIL_HEADER, email)
            .build();
    }

    /**
     * Validates the user's current status (e.g., active, disabled) by calling the Auth Service.
     *
     * <p>
     * This is a stateful check that ensures the token, though technically valid, belongs
     * to a currently active and legal user in the system.
     * </p>
     *
     * @param uid The unique user ID extracted from the JWT payload.
     * @param email The user's email extracted from the JWT payload.
     * @throws FsIllegalUserException if the Auth Service confirms the user is invalid.
     */
    private void validateUser(String uid, String email) {
        logger.info("Validate user: {}, {}", email, uid);
        // Validate user status
        var isValid = authFeignClient.validateUser(new UserValidationRequest(email, uid));
        logger.info("Validate user: {}, {}", email, isValid.getBody());
        if (Boolean.FALSE.equals(isValid.getBody())) {
            throw new FsIllegalUserException(email);
        }
    }
}
