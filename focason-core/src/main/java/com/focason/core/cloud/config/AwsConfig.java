package com.focason.core.cloud.config;



import com.focason.core.properties.AwsProps;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
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
 * AWSサービス接続設定
 *
 * @since 1.0
 */
@EnableConfigurationProperties(AwsProps.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
public class AwsConfig
{
    /**
     * AWSプロパティ
     */
    private final AwsProps awsProps;

    /**
     * AWS環境上のS3証明付きリクエストクライアント接続
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
     * AWS環境上のS3クライアント接続
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
     * AWS環境上のRekognitionクライアント接続
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
     * AWS環境上のCognitoIdentityクライアント
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
     * Generate secret hash by condition.
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
            throw new RuntimeException("errors in HMAC calculation");
        }
    }
}
