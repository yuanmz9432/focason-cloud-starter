// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.aws.config;



import com.focason.platform.aws.service.S3Service;
import com.focason.platform.properties.FsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

/**
 * AWS Service Default Client Configuration
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Configuration
public class AwsDefaultServiceConfig
{
    /**
     * Focason properties (contains AWS configuration)
     */
    private final FsProperties fsProperties;

    @Bean
    public S3Service s3Service(S3Presigner s3Presigner, S3Client s3Client) {
        var props = fsProperties.getCloud().getAws().getS3().get("file-config");
        return S3Service.builder()
            .s3Presigner(s3Presigner)
            .s3Client(s3Client)
            .prefix(props.getPrefix())
            .bucketName(props.getBucketName())
            .preSignedUrlValidMinutes(props.getPreSignedUrlValidMinutes())
            .build();
    }
}
