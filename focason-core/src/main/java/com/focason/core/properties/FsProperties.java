package com.focason.core.properties;


import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = FsProperties.PREFIX)
@Data
public class FsProperties
{
    public static final String PREFIX = "focason";

    private Map<String, String> email;
}
