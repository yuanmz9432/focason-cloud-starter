package com.focason.core.properties;



import lombok.Data;

@Data
public class CognitoProps
{
    private String userPoolId;
    private String clientId;
    private String clientSecret;
}
