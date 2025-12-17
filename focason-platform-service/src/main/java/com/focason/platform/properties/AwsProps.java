package com.focason.platform.properties;



import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AwsProps.PREFIX)
@Data
public class AwsProps
{
    public static final String PREFIX = "focason.cloud.aws";

    private String region;

    private Map<String, S3Props> s3;

    private CognitoProps cognito;

    private Map<String, SqsProps> sqs;
}
