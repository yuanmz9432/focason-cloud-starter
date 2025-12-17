// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.aws.config;



import com.focason.platform.aws.service.S3Service;
import com.focason.platform.aws.service.SqsService;
import com.focason.platform.properties.CloudProps;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.sqs.SqsClient;

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
    private final CloudProps cloudProps;

    @Bean
    public S3Service s3Service(S3Presigner s3Presigner, S3Client s3Client) {
        var props = cloudProps.getAws().getS3().get("file-config");
        return S3Service.builder()
            .s3Presigner(s3Presigner)
            .s3Client(s3Client)
            .prefix(props.getPrefix())
            .bucketName(props.getBucketName())
            .preSignedUrlValidMinutes(props.getPreSignedUrlValidMinutes())
            .build();
    }

    @Bean
    public SqsService sqsService(SqsClient sqsClient) {
        var sqsMap = cloudProps.getAws().getSqs();
        if (sqsMap == null || sqsMap.isEmpty()) {
            throw new IllegalStateException("SQS configuration is missing");
        }

        // Get the first SQS configuration (you can modify this to support multiple queues)
        var props = sqsMap.values().iterator().next();

        return SqsService.builder()
            .sqsClient(sqsClient)
            .queueUrl(props.getQueueUrl())
            .maxNumberOfMessages(props.getMaxNumberOfMessages())
            .waitTimeSeconds(props.getWaitTimeSeconds())
            .visibilityTimeout(props.getVisibilityTimeout())
            .build();
    }
}
