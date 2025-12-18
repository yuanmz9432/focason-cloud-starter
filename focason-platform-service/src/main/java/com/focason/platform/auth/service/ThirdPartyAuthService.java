// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.auth.service;


import com.focason.core.domain.Switch;
import com.focason.core.entity.Base001UserEntity;
import com.focason.core.resource.GoogleTokenResource;
import com.focason.core.resource.GoogleUserResource;
import com.focason.core.resource.UserResource;
import com.focason.core.response.UserLoginResponse;
import com.focason.core.utility.FsUtilityToolkit;
import com.focason.platform.user.repository.UserRepository;
import com.google.gson.JsonObject;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ThirdPartyAuthService
{
    final Logger logger = LoggerFactory.getLogger(ThirdPartyAuthService.class);

    private final RestTemplate restTemplate = new RestTemplate();


    private final UserRepository userRepository;
    private final AuthenticationService authService;

    public GoogleTokenResource exchangeCodeForToken(String code) {

        final String TOKEN_URL = "https://oauth2.googleapis.com/token";
        MultiValueMap<String, String> params = getRequestParamMap(code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<GoogleTokenResource> response =
            restTemplate.postForEntity(TOKEN_URL, request, GoogleTokenResource.class);

        return response.getBody();
        // try {
        // ResponseEntity<String> response =
        // restTemplate.postForEntity(TOKEN_URL, request, String.class);
        // System.out.println("Google token response: " + response.getBody());
        // } catch (HttpClientErrorException e) {
        // System.out.println("Error body: " + e.getResponseBodyAsString());
        // }
        // return null;
    }

    @NotNull
    private static MultiValueMap<String, String> getRequestParamMap(String code) {
        final String CLIENT_ID = "224710351583-h1csifa4kfpsvjlrlj1u8onachk5kodf.apps.googleusercontent.com";
        final String CLIENT_SECRET = "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";
        final String REDIRECT_URI = "https://focason.com/callback/google";
        // final String REDIRECT_URI = "http://localhost:8080/callback/google";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("grant_type", "authorization_code");
        return params;
    }

    public GoogleUserResource parseIdToken(String idToken) {
        logger.info("idToken: " + idToken);
        try {
            JsonObject payload = Optional.ofNullable(FsUtilityToolkit.decodeJwtPayload(idToken))
                .orElseThrow(() -> new RuntimeException(idToken));

            GoogleUserResource googleUserResource = new GoogleUserResource();
            googleUserResource.setSub(payload.get("sub").getAsString());
            googleUserResource.setEmail(payload.get("email").getAsString());
            googleUserResource.setName(payload.get("name").getAsString());
            googleUserResource.setPicture(payload.get("picture").getAsString());
            return googleUserResource;
        } catch (Exception e) {
            throw new RuntimeException("解析 Google ID Token 失败", e);
        }
    }

    @Transactional
    public UserLoginResponse authenticateWithGoogle(GoogleUserResource googleUserResource) {
        // 用email检索user
        var user = userRepository.findUserByEmail(googleUserResource.getEmail());
        // 没有，登录user
        Base001UserEntity entity = new Base001UserEntity();
        if (user.isEmpty()) {
            entity.setUid(googleUserResource.getSub());
            // entity.setUsername(googleUserResource.getName());
            entity.setEmail(googleUserResource.getEmail());
            entity.setStatus(Switch.ON.getValue());
            // Create User.
            userRepository.create(entity);
        }
        // 创建token并返回
        return authService.generateLoginResponse(FsUtilityToolkit.convert(entity, UserResource.class));
    }
}
