package com.focason.core.request;



import lombok.Data;

/**
 * RefreshTokenRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class RefreshTokenRequest
{
    /** ユーザー識別子 */
    private String uid;
    /** デバイスID */
    private String deviceId;
}
