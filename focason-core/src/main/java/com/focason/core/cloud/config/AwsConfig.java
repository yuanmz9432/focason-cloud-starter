// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.cloud.config;

import com.focason.core.properties.AwsProps;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

/**
 * Configuration for AWS Service Connections
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EnableConfigurationProperties(AwsProps.class)
@Configuration
public class AwsConfig
{
    /**
     * AWS properties
     */
    private final AwsProps awsProps;

    /**
     * Manual Constructor Injection (replaces Lombok's @RequiredArgsConstructor)
     */
    @Autowired
    public AwsConfig(AwsProps awsProps) {
        this.awsProps = awsProps;
    }

    /**
     * Provides the S3Presigner client for generating pre-signed URLs.
     *
     * @return {@link S3Presigner}
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
            .region(Region.of(awsProps.getRegion()))
            .build();
    }

    /**
     * Provides the S3 client for standard AWS S3 operations.
     *
     * @return {@link S3Client}
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    public S3Client s3Client() {
        return S3Client.builder()
            .region(Region.of(awsProps.getRegion()))
            .build();
    }

    /**
     * Provides the Rekognition client for image and video analysis.
     *
     * @return {@link RekognitionClient}
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    public RekognitionClient rekClient() {
        return RekognitionClient.builder()
            .region(Region.of(awsProps.getRegion()))
            .build();
    }

    /**
     * Provides the Cognito Identity Provider client.
     *
     * @return {@link CognitoIdentityProviderClient}
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    public CognitoIdentityProviderClient cognitoIdentityProviderClient() {
        return CognitoIdentityProviderClient.builder()
            .region(Region.of(awsProps.getRegion()))
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build();
    }

    /**
     * Generate secret hash by condition. This utility method is often required for
     * authentication flows in AWS Cognito.
     *
     * @param userId the user id.
     * @param clientId AWS Security Client ID.
     * @param clientSecret AWS Security Client Secret.
     * @return the secret hash.
     */
    public static String generateSecretHash(String userId, String clientId, String clientSecret) {
        final String HMAC_SHA_256 = "HmacSHA256";
        final SecretKeySpec signingKey = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8),
            HMAC_SHA_256);
        try {
            final Mac mac = Mac.getInstance(HMAC_SHA_256);
            mac.init(signingKey);
            mac.update(userId.getBytes(StandardCharsets.UTF_8));
            final byte[] rawHmac = mac.doFinal(clientId.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(rawHmac));
        } catch (final Exception e) {
            // Added 'e' to the exception constructor for better error reporting.
            throw new RuntimeException("Errors in HMAC calculation", e);
        }
    }
}
