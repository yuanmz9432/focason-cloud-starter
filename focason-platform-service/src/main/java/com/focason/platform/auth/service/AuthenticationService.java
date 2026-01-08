// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.auth.service;


import com.focason.core.domain.DeviceType;
import com.focason.core.domain.EmailType;
import com.focason.core.domain.Switch;
import com.focason.core.domain.TokenType;
import com.focason.core.entity.Base003UserTokenEntity;
import com.focason.core.exception.*;
import com.focason.core.resource.*;
import com.focason.core.response.UserLoginResponse;
import com.focason.core.utility.FsUtilityToolkit;
import com.focason.platform.consumer.EmailSqsConsumer;
import com.focason.platform.user.repository.UserRepository;
import com.focason.platform.user.service.UserService;
import com.google.gson.JsonElement;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthenticationService
 * <p>
 * This service class encapsulates the core business logic for user authentication
 * and authorization workflows, including user login, registration, token management,
 * and password recovery processes.
 *
 * <p>
 * It interacts with data repositories (JPA), message queues (RabbitMQ), and
 * caching mechanisms (Redis) to ensure secure and transactional operations.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see com.focason.platform.user.repository.UserRepository
 * @see org.springframework.stereotype.Service
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService
{
    final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    final private UserService userService;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;
    private final SqsTemplate sqsTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Authenticates a user based on email and password.
     *
     * <p>
     * The process involves finding the user, checking their status, validating the password,
     * and generating the necessary access and refresh tokens upon success.
     * </p>
     *
     * @param resource {@link UserResource} containing the email and plaintext password.
     * @return {@link UserLoginResponse} containing tokens and user details.
     * @throws FsResourceNotFoundException If the user with the given email does not exist.
     * @throws FsIllegalUserException If the user's status is inactive (Switch.OFF).
     * @throws FsInvalidPasswordException If the provided password does not match the stored hash.
     */
    @Transactional
    public UserLoginResponse login(@NotNull UserResource resource) {
        // Find user.
        var userEntity = userRepository.findUserByEmail(resource.getEmail())
            .orElseThrow(() -> new FsResourceNotFoundException(UserResource.class, resource.getEmail()));
        // Check user is valid or not.
        if (Switch.OFF.getValue().equals(userEntity.getStatus())) {
            throw new FsIllegalUserException(resource.getEmail());
        }
        // Check password.
        if (FsUtilityToolkit.isPasswordMismatched(resource.getPassword(), userEntity.getPassword())) {
            throw new FsInvalidPasswordException();
        }
        // Populate resource with necessary entity IDs/UIDs
        resource.setId(userEntity.getId());
        resource.setUid(userEntity.getUid());
        resource.setUsername(userEntity.getUsername());
        // Generate token and return user info.
        return generateLoginResponse(resource);
    }

    /**
     * Registers a new user account after validating the verification code.
     *
     * <p>
     * It checks the validity of the email verification code stored in Redis before
     * creating and persisting the new user record.
     * </p>
     *
     * @param resource {@link UserResource} containing email, verification code, and password.
     * @return {@link UserLoginResponse} for the newly registered and logged-in user.
     * @throws FsVerificationCodeExpiredException If the code in Redis has expired.
     * @throws FsInvalidVerificationCodeException If the provided code does not match the stored code.
     */
    @Transactional
    public UserLoginResponse register(@NotNull UserResource resource) {
        // Verify code.
        String code = (String) redisTemplate.opsForValue().get("code:email:" + resource.getEmail());
        // Verification code was expired.
        if (code == null)
            throw new FsVerificationCodeExpiredException();
        // Verification code not match.
        if (!code.equals(resource.getVerificationCode()))
            throw new FsInvalidVerificationCodeException();
        // Generate user uid.
        final var uid = FsUtilityToolkit.generateUUID();
        resource.setUid(uid);
        resource.setUsername("Guest");
        resource.setStatus(Switch.ON.getValue());
        // Save user.
        userService.create(resource);
        return generateLoginResponse(resource);
    }

    /**
     * Generates a new verification code and dispatches it via email queue.
     *
     * <p>
     * The code is simultaneously saved to Redis with a 10-minute expiration time.
     * </p>
     *
     * @param email The target email address to send the code to.
     * @throws FsUserVerifiedException If the user associated with the email is already verified.
     */
    @Transactional
    public void sendVerificationCode(String email) {
        // Find user.
        // Find user by email.
        userRepository.findUserByEmail(email)
            .ifPresent(item -> {
                // If user is valid, return message.
                if (item.getStatus().equals(Switch.ON.getValue()))
                    throw new FsUserVerifiedException();
            });

        // Generate verification code.
        var verificationCode = FsUtilityToolkit.generate6DigitVerificationCode();
        Map<String, Object> payload = Map.of("verificationCode", verificationCode);
        var message = EmailResource.builder()
            .to(email)
            .emailType(EmailType.REGISTER.getValue())
            .payload(payload)
            .build();
        // Send email message to the RabbitMQ queue.
        // rabbitTemplate.convertAndSend(EmailQueueConfig.EMAIL_SEND_QUEUE, message);
        sqsTemplate.send(Objects.requireNonNull(EmailSqsConsumer.QUEUE_NAME), Objects.requireNonNull(message));
        // Save verification code to redis with 10-minute expiry.
        Duration codeExpiryDuration = Objects.requireNonNull(Duration.ofMinutes(10));
        redisTemplate.opsForValue().set("code:email:" + email, Objects.requireNonNull(verificationCode),
            codeExpiryDuration);
    }

    /**
     * Generates a new access token and refresh token pair and updates the database record.
     *
     * <p>
     * This is a utility method used by both {@code login} and {@code register} to
     * finalize the authentication state.
     * </p>
     *
     * @param resource {@link UserResource} containing user identity details (UID).
     * @return {@link UserLoginResponse} with the newly generated token pair.
     */
    @Transactional
    public UserLoginResponse generateLoginResponse(UserResource resource) {
        // Generate access token and refresh token.
        var expiresAt = FsUtilityToolkit.generateExpirationTime(TokenType.ACCESS_TOKEN);
        var expiresIn = FsUtilityToolkit.ACCESS_TOKEN_EXPIRATION_TIME;
        var accessToken = FsUtilityToolkit.generateAccessToken(resource, expiresAt);
        var refreshToken = FsUtilityToolkit.generateAccessToken(resource,
            FsUtilityToolkit.generateExpirationTime(TokenType.REFRESH_TOKEN));
        var deviceId = resource.getDeviceId() != null ? resource.getDeviceId() : FsUtilityToolkit.generateUUID();
        var deviceType = resource.getDeviceType() != null ? resource.getDeviceType() : DeviceType.UNKNOWN.getValue();

        // Save user token.
        var entity = new Base003UserTokenEntity();
        entity.setUid(resource.getUid());
        entity.setAccessToken(accessToken);
        entity.setRefreshToken(refreshToken);
        entity.setExpiresAt(expiresAt);
        entity.setDeviceId(deviceId);
        entity.setDeviceType(deviceType);
        userRepository.saveUserToken(entity);

        return new UserLoginResponse(
            accessToken,
            expiresIn,
            expiresAt,
            refreshToken,
            deviceId,
            FsUtilityToolkit.convert(resource, UserLoginResponse.User.class));
    }

    /**
     * Refreshes the access token using a valid refresh token.
     *
     * <p>
     * The method validates the refresh token against the database, checks expiration,
     * and issues a new access token and a new refresh token (token rotation).
     * </p>
     *
     * @param refreshToken The refresh token string (expected to be in Bearer format from header).
     * @param resource {@link UserResource} containing the UID and Device ID for lookup.
     * @return {@link AuthResource} containing the new access and refresh tokens.
     * @throws FsIllegalRefreshTokenException If the token is invalid, malformed, or does not match the database record.
     * @throws FsRefreshTokenExpiredException If the token has passed its expiration time.
     * @throws FsIllegalUserException If the associated user is inactive.
     */
    @Transactional
    public AuthResource refreshToken(String refreshToken, @NotNull UserResource resource) {
        // Parse Token and get UID and expiration time.
        var payload = FsUtilityToolkit.decodeJwtPayload(refreshToken);
        var sub = Optional.ofNullable(payload.get(FsUtilityToolkit.CLAIMS_SUB))
            .map(JsonElement::getAsString)
            .orElseThrow(FsIllegalRefreshTokenException::new);
        var timestamp = Optional.ofNullable(payload.get(FsUtilityToolkit.CLAIMS_EXP))
            .map(JsonElement::getAsLong)
            .orElseThrow(FsIllegalRefreshTokenException::new);

        // Find user token by uid and deviceId.
        var userToken = userRepository.findUserToken(resource.getUid(), resource.getDeviceId())
            .orElseThrow(FsIllegalRefreshTokenException::new);

        // Verify Refresh Token in the database.
        if (!refreshToken.replace("Bearer ", "").equals(userToken.getRefreshToken())) {
            logger.warn("Refresh token validation failed for user: {}", sub);
            throw new FsIllegalRefreshTokenException();
        }

        // Verify Refresh Token expiration.
        long nowTimestamp = FsUtilityToolkit.toTokyoEpochSeconds(LocalDateTime.now());
        if (timestamp < nowTimestamp) {
            throw new FsRefreshTokenExpiredException();
        }

        // Look up user information.
        var userEntity = userRepository.findUserByUid(sub)
            .orElseThrow(() -> new FsIllegalUserException(sub));

        // Verify user status.
        if (Switch.OFF.getValue().equals(userEntity.getStatus())) {
            throw new FsIllegalUserException(userEntity.getEmail());
        }

        // Generate new access token and refresh token.
        var expiresAt = FsUtilityToolkit.generateExpirationTime(TokenType.REFRESH_TOKEN);
        var newAccessToken =
            FsUtilityToolkit.generateAccessToken(FsUtilityToolkit.convert(userEntity, UserResource.class),
                FsUtilityToolkit.generateExpirationTime(TokenType.ACCESS_TOKEN));
        var newRefreshToken =
            FsUtilityToolkit.generateRefreshToken(FsUtilityToolkit.convert(userEntity, UserResource.class),
                expiresAt);

        // Update Refresh Token in the database (Token Rotation).
        userToken.setAccessToken(newAccessToken);
        userToken.setRefreshToken(newRefreshToken);
        userToken.setExpiresAt(expiresAt);
        userRepository.updateUserToken(userToken);

        return AuthResource.builder()
            .accessToken(newAccessToken)
            .refreshToken(newRefreshToken)
            .expiresIn(FsUtilityToolkit.toTokyoEpochSeconds(expiresAt))
            .build();
    }

    /**
     * Performs user logout by deleting the token record associated with the device.
     *
     * <p>
     * This effectively revokes the refresh token and forces the user to log in again
     * after the access token expires.
     * </p>
     *
     * @param resource {@link UserResource} containing the UID and Device ID.
     */
    @Transactional
    public void logout(@NotNull UserResource resource) {
        var userToken = userRepository.findUserToken(resource.getUid(), resource.getDeviceId());
        // If token exists for the device, delete it.
        userToken.ifPresent(userRepository::deleteUserToken);
    }

    /**
     * Validates if a user's email, UID, and status are consistent and valid.
     *
     * <p>
     * This method is typically used by internal services to verify user legitimacy
     * before granting access to resources.
     * </p>
     *
     * @param resource {@link UserResource} containing the email and expected UID.
     * @return {@code true} if the user exists, the UID matches, and the status is ON; otherwise {@code false}.
     * @throws FsResourceNotFoundException If the user with the given email does not exist.
     */
    @Transactional
    public boolean validateUser(@NotNull UserResource resource) {
        // Find user by email.
        var user = userRepository.findUserByEmail(resource.getEmail())
            .orElseThrow(() -> new FsResourceNotFoundException(UserResource.class, resource.getEmail()));
        // Checks if the UID matches and the user is active.
        return user.getUid().equals(resource.getUid()) && Switch.ON.getValue().equals(user.getStatus());
    }

    /**
     * Validates if an access token exists in the database.
     *
     * <p>
     * This method is typically used by gateway services to verify if a token
     * is still valid and has not been revoked (e.g., after logout).
     * </p>
     *
     * @param accessToken The access token string to validate.
     * @return {@code true} if the token exists in the database; otherwise {@code false}.
     */
    @Transactional
    public boolean validateAccessToken(@NotNull String accessToken) {
        // Remove "Bearer " prefix if present
        String token = accessToken.replace("Bearer ", "").trim();
        // Find token in database
        return userRepository.findUserTokenByAccessToken(token).isPresent();
    }

    /**
     * Initiates the "Forgot Password" process.
     *
     * <p>
     * It encrypts the user's UID and creates a password reset link, then queues an
     * email containing the link. The UID is temporarily stored in Redis for link validation.
     * </p>
     *
     * @param email The email address of the user who requested the password reset.
     * @throws FsResourceNotFoundException If the user with the given email does not exist.
     */
    @Transactional
    public void forgotPassword(@NotNull String email) {
        // Find user by email.
        var user = userRepository.findUserByEmail(email)
            .orElseThrow(() -> new FsResourceNotFoundException(UserResource.class, email));

        // Generate reset link (contains encrypted UID).
        String resetLink = "http://localhost:3000/reset-password?uid=" + FsUtilityToolkit.encrypt(user.getUid());

        Map<String, Object> payload = Map.of("resetLink", resetLink);
        var message = EmailResource.builder()
            .to(email)
            .emailType(EmailType.FORGOT_PASSWORD.getValue())
            .payload(payload)
            .build();
        // Send email to RabbitMQ queue.
        rabbitTemplate.convertAndSend(EmailSqsConsumer.QUEUE_NAME, message);
        // Save UID to redis for link validation with 30-minute expiry.
        Duration expiryDuration = Objects.requireNonNull(Duration.ofMinutes(30));
        redisTemplate.opsForValue().set("reset-password:email:" + email, Objects.requireNonNull(user.getUid()),
            expiryDuration);
    }

    /**
     * Resets the user's password after validating the reset link via Redis key.
     *
     * <p>
     * The method decrypts the UID, validates the associated Redis key, updates the password
     * hash in the database, and performs an immediate login.
     * </p>
     *
     * @param resource {@link UserResource} containing the encrypted UID and the new plaintext password.
     * @return {@link UserLoginResponse} for the user after the successful password reset and login.
     * @throws FsResourceNotFoundException If the user UID is invalid or user does not exist.
     * @throws FsResetPasswordFailedException If the Redis key (link) is expired or does not match the UID.
     */
    @Transactional
    public UserLoginResponse resetPassword(UserResource resource) {
        var uid = FsUtilityToolkit.decrypt(resource.getUid());
        // Find user by UID.
        var user = userRepository.findUserByUid(uid)
            .orElseThrow(() -> new FsResourceNotFoundException(UserResource.class, uid));

        // Verify code (check if the reset link is still valid and matches the user).
        String key = (String) redisTemplate.opsForValue().get("reset-password:email:" + user.getEmail());
        if (key == null || !Objects.equals(key, uid))
            throw new FsResetPasswordFailedException(user.getEmail());

        // Update password hash.
        user.setPassword(FsUtilityToolkit.encryptPassword(resource.getPassword()));
        userRepository.update(user);

        // Perform login for the user.
        return generateLoginResponse(FsUtilityToolkit.convert(user, UserResource.class));
    }
}
