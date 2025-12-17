package com.focason.platform.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Focason Top-level Configuration Properties
 * <p>
 * This is the root configuration class that aggregates all focason.* properties.
 * All sub-configurations (email, cloud, etc.) are nested under this class.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = FsProperties.PREFIX)
@Data
public class FsProperties
{
    public static final String PREFIX = "focason";

    /**
     * Email configuration properties
     */
    private EmailProps email;

    /**
     * Cloud configuration properties (AWS, etc.)
     */
    private CloudProps cloud;
}
