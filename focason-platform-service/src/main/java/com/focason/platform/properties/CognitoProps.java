package com.focason.platform.properties;



import lombok.Data;

@Data
public class CognitoProps
{
    private String userPoolId;
    private String clientId;
    private String clientSecret;
}
