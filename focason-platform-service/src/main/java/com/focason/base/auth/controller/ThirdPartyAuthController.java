// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.base.auth.controller;


import com.focason.base.auth.service.ThirdPartyAuthService;
import com.focason.core.resource.GoogleTokenResource;
import com.focason.core.resource.GoogleUserResource;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ThirdPartyAuthController
 * <p>
 * This controller handles the OAuth 2.0 callback for third-party authentication
 * providers, specifically Google. It manages the token exchange process and
 * delegates the user authentication/registration to the corresponding service.
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
 * @see ThirdPartyAuthService
 * @see org.springframework.web.bind.annotation.RestController
 */
@RestController
@RefreshScope
@AllArgsConstructor(onConstructor = @__({
    @Autowired
}))
public class ThirdPartyAuthController
{
    final Logger logger = LoggerFactory.getLogger(ThirdPartyAuthController.class);

    private final ThirdPartyAuthService service;
    private final String CALLBACK_BY_GOOGLE = "/api/v1/auth/google/callback";

    /**
     * Handles the OAuth 2.0 callback from Google upon successful user consent.
     *
     * <p>
     * This method performs a three-step process:
     * 1. Exchanges the authorization code for an ID Token and Access Token.
     * 2. Parses the ID Token to extract user information (email, name, etc.).
     * 3. Authenticates or registers the user within the local system.
     * </p>
     *
     * @param code The authorization code received from the Google server after user approval.
     * @return {@link ResponseEntity} containing the authentication response (e.g., tokens) after
     *         successful internal authentication with the Google user data.
     * @apiNote HTTP Status: 200 OK. Mapped to a GET request as per standard OAuth callback flow.
     */
    @RequestMapping(method = RequestMethod.GET, value = CALLBACK_BY_GOOGLE)
    public ResponseEntity<?> callbackByGoogle(@RequestParam("code") String code) {
        logger.debug("code={}", code);
        // Step 1: Exchange code for token (Access Token & ID Token)
        GoogleTokenResource googleTokenResource = service.exchangeCodeForToken(code);

        // Step 2: Parse id_token to extract user information (email, name, picture, etc.)
        GoogleUserResource googleUserResource = service.parseIdToken(googleTokenResource.getIdToken());

        // Step 3: Authenticate/Register user in the local system using Google details
        return ResponseEntity.ok(service.authenticateWithGoogle(googleUserResource));
    }

}
