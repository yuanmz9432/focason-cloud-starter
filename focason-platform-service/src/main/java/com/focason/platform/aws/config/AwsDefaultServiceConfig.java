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

    /**
     * Create a generic SqsService that can work with multiple queues.
     * The service doesn't bind to a specific queue, allowing you to specify
     * the queue URL when calling methods.
     *
     * @param sqsClient The SQS client
     * @return SqsService instance
     */
    @Bean
    public SqsService sqsService(SqsClient sqsClient) {
        var sqsMap = cloudProps.getAws().getSqs();
        if (sqsMap == null || sqsMap.isEmpty()) {
            throw new IllegalStateException("SQS configuration is missing");
        }

        // Get the first SQS configuration as default (for backward compatibility)
        // All methods now support optional queueUrl parameter
        var defaultProps = sqsMap.values().iterator().next();

        return SqsService.builder()
            .sqsClient(sqsClient)
            .queueUrl(defaultProps.getQueueUrl()) // Default queue URL (can be overridden in method calls)
            .maxNumberOfMessages(defaultProps.getMaxNumberOfMessages())
            .waitTimeSeconds(defaultProps.getWaitTimeSeconds())
            .visibilityTimeout(defaultProps.getVisibilityTimeout())
            .build();
    }
}
