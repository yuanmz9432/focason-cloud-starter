package com.focason.platform.properties;

import lombok.Data;

/**
 * Cloud Configuration Properties
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class CloudProps
{
    /**
     * AWS configuration
     */
    private AwsProps aws;
}
