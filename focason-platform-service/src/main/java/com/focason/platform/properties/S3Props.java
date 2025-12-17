package com.focason.platform.properties;



import lombok.Data;

@Data
public class S3Props
{
    private String bucketName;
    private String prefix;
    private long preSignedUrlValidMinutes;
}
