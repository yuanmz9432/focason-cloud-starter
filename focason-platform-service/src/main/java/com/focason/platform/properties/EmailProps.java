package com.focason.platform.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = EmailProps.PREFIX)
public class EmailProps
{
    public static final String PREFIX = "focason.email";
    private String sendBy;
    private String sendFrom;
}
