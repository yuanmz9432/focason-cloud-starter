package com.focason.platform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Cloud Configuration Properties
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = CloudProps.PREFIX)
public class CloudProps
{
    public static final String PREFIX = "focason.cloud";
    /**
     * AWS configuration
     */
    private AwsProps aws;
}
