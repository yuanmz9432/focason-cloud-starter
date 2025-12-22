package com.focason.platform.properties;



import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
@Component
@ConfigurationProperties(prefix = AwsProps.PREFIX)
public class AwsProps
{
    public static final String PREFIX = "focason.cloud.aws";
    /**
     * AWS Region (e.g., ap-northeast-1, us-east-1)
     */
    private String region;

    /**
     * S3 bucket configurations (supports multiple buckets)
     */
    private Map<String, S3Props> s3;
}
