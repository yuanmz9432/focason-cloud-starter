// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.auth.controller;



import com.focason.core.request.*;
import com.focason.core.resource.UserResource;
import com.focason.core.response.RefreshTokenResponse;
import com.focason.core.response.UserLoginResponse;
import com.focason.core.utility.FsUtilityToolkit;
import com.focason.platform.auth.service.AuthenticationService;
import com.focason.platform.aws.service.SqsServiceManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthenticationController
 * <p>
 * This controller is responsible for handling all external API requests
 * related to user authentication, including login, logout, token refresh,
 * and user session validation. It acts as the primary entry point for
 * securing the service and often interacts directly with security providers
 * or an underlying authentication service layer.
 *
 * <p>
 * It is configured with {@code @RefreshScope} to allow dynamic
 * updates to configuration properties without needing a full application restart.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.cloud.context.config.annotation.RefreshScope
 */
@RestController
@RefreshScope
@AllArgsConstructor(onConstructor = @__({
    @Autowired
}))
public class AuthenticationController
{
    final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService service;
    private final String LOGIN_URL = "/api/v1/auth/login";
    private final String LOGOUT_URL = "/api/v1/auth/logout";
    private final String REGISTER_URL = "/api/v1/auth/register";
    private final String SEND_VERIFICATION_CODE_URL = "/api/v1/auth/send-verification-code";
    private final String REFRESH_TOKEN_URL = "/api/v1/auth/refresh-token";
    private final String FORGOT_PASSWORD_URL = "/api/v1/auth/forgot-password";
    private final String RESET_PASSWORD_URL = "/api/v1/auth/reset-password";
    private final String VALIDATE_USER_URL = "/api/v1/auth/validate-user";

    /** Application properties containing configuration details like send-from address. */
    private final SqsServiceManager sqsServiceManager;

    /**
     * Handles user login process.
     *
     * <p>
     * This method validates user credentials and issues an initial access token
     * and refresh token upon successful authentication.
     * </p>
     *
     * @param request The login credentials containing username/email and password.
     * @return {@link ResponseEntity} containing {@link UserLoginResponse} with user details and tokens.
     * @apiNote HTTP Status: 200 OK on success; 401 Unauthorized on failure.
     */
    @RequestMapping(method = RequestMethod.POST, value = LOGIN_URL)
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        logger.debug("login request: {}", request);
        var queue = sqsServiceManager.getQueueUrl("focason-email-send-queue");

        logger.info("email: {}", queue.toString());
        return ResponseEntity.ok(service.login(FsUtilityToolkit.convert(request, UserResource.class)));
    }

    /**
     * Handles user logout process.
     *
     * <p>
     * This method invalidates the user's current session or revokes the refresh token.
     * </p>
     *
     * @param request The logout request, typically containing the refresh token or session ID.
     * @return {@link ResponseEntity} with status 204 No Content upon successful logout.
     * @apiNote HTTP Status: 204 No Content.
     */
    @RequestMapping(method = RequestMethod.POST, value = LOGOUT_URL)
    public ResponseEntity<UserLoginResponse> logout(@RequestBody UserLogoutRequest request) {
        logger.debug("logout request: {}", request);
        service.logout(FsUtilityToolkit.convert(request, UserResource.class));
        return ResponseEntity.noContent().build();
    }

    /**
     * Creates and registers a new user account.
     *
     * <p>
     * Upon successful registration, the method may automatically log in the user
     * and return authentication tokens.
     * </p>
     *
     * @param request The registration details, including required user information.
     * @return {@link ResponseEntity} containing {@link UserLoginResponse} with the newly created user's tokens.
     * @apiNote HTTP Status: 200 OK on success (or 201 Created depending on service implementation).
     */
    @RequestMapping(method = RequestMethod.POST, value = REGISTER_URL)
    public ResponseEntity<UserLoginResponse> register(@RequestBody UserRegisterRequest request) {
        logger.debug("register request: {}", request);
        return ResponseEntity.ok(service.register(FsUtilityToolkit.convert(request, UserResource.class)));
    }

    /**
     * Sends a new verification code to the specified email address.
     *
     * <p>
     * This endpoint handles requests to resend a forgotten or expired verification code.
     * </p>
     *
     * @param request The request containing the target email address.
     * @return {@link ResponseEntity} with status 200 OK upon successful code dispatch.
     * @apiNote HTTP Status: 200 OK.
     */
    @RequestMapping(method = RequestMethod.POST, value = SEND_VERIFICATION_CODE_URL)
    public ResponseEntity<Void> sendVerificationCode(@RequestBody SendVerificationCodeRequest request) {
        logger.debug("sendVerificationCode request: {}", request);
        service.sendVerificationCode(request.email());
        return ResponseEntity.ok().build();
    }

    /**
     * Refreshes a short-lived access token using a long-lived refresh token.
     *
     * <p>
     * This method is essential for maintaining a user's session without forcing re-login.
     * </p>
     *
     * @param refreshToken The refresh token passed in the Authorization header (e.g., Bearer token format).
     * @param request The request body, typically used for carrying user context or device info.
     * @return {@link ResponseEntity} containing {@link RefreshTokenResponse} with the new access token.
     * @apiNote HTTP Status: 200 OK on success; 403 Forbidden if refresh token is expired or invalid.
     */
    @RequestMapping(method = RequestMethod.POST, value = REFRESH_TOKEN_URL)
    public ResponseEntity<RefreshTokenResponse> refreshToken(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken,
        @RequestBody RefreshTokenRequest request) {
        logger.debug("refreshToken request: {}", request);
        var authResource = service.refreshToken(refreshToken, FsUtilityToolkit.convert(request, UserResource.class));
        return ResponseEntity.ok(
            FsUtilityToolkit.convert(authResource, RefreshTokenResponse.class));
    }

    /**
     * Initiates the forgotten password workflow.
     *
     * <p>
     * This usually involves sending a password reset link or a one-time code to the user's email.
     * </p>
     *
     * @param request The request containing the user's email address.
     * @return {@link ResponseEntity} with status 200 OK, regardless of whether the email exists (to prevent user
     *         enumeration attacks).
     * @apiNote HTTP Status: 200 OK.
     */
    @RequestMapping(method = RequestMethod.POST, value = FORGOT_PASSWORD_URL)
    public ResponseEntity<Void> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        logger.debug("forgotPassword request: {}", request);
        service.forgotPassword(request.email());
        return ResponseEntity.ok().build();
    }

    /**
     * Completes the password reset process.
     *
     * <p>
     * Requires a valid token or code received from the forgot password process, along with the new password.
     * </p>
     *
     * @param request The request containing the reset token/code and the new password.
     * @return {@link ResponseEntity} containing {@link UserLoginResponse} if the user is automatically logged in after
     *         reset.
     * @apiNote HTTP Status: 200 OK. Uses PUT method as it is a resource update operation.
     */
    @RequestMapping(method = RequestMethod.PUT, value = RESET_PASSWORD_URL)
    public ResponseEntity<UserLoginResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        logger.debug("resetPassword request: {}", request);
        return ResponseEntity.ok(service.resetPassword(FsUtilityToolkit.convert(request, UserResource.class)));
    }

    /**
     * Validates a user's existence or credential status without performing a full login.
     *
     * <p>
     * This endpoint is often used by gateway services or other internal services
     * to check if a user is legitimate before proceeding with complex operations.
     * </p>
     *
     * @param request The validation request, typically containing user credentials or partial identifiers.
     * @return {@link ResponseEntity} containing a boolean result: true if validation succeeds, false otherwise.
     * @apiNote HTTP Status: 200 OK.
     */
    @RequestMapping(method = RequestMethod.POST, value = VALIDATE_USER_URL)
    public ResponseEntity<Boolean> validateUser(@RequestBody UserValidationRequest request) {
        logger.debug("validateUser request: {}", request);
        return ResponseEntity.ok(service.validateUser(FsUtilityToolkit.convert(request, UserResource.class)));
    }
}
