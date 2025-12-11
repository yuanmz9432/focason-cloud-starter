package com.focason.core.request;



import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * RefreshTokenRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class RefreshTokenRequest
{
    /** ユーザー識別子 */
    private String sub;
    /** デバイスID */
    private String deviceId;
}
