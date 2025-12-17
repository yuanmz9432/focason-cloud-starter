package com.focason.platform.properties;



import java.util.Map;
import lombok.Data;

/**
 * AWS Configuration Properties
 * <p>
 * Nested under focason.cloud.aws in configuration files.
 * This class is now a nested property of CloudProps.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class AwsProps
{
    /**
     * AWS Region (e.g., ap-northeast-1, us-east-1)
     */
    private String region;

    /**
     * S3 bucket configurations (supports multiple buckets)
     */
    private Map<String, S3Props> s3;

    /**
     * Cognito configuration
     */
    private CognitoProps cognito;

    /**
     * SQS queue configurations (supports multiple queues)
     */
    private Map<String, SqsProps> sqs;
}
