package com.focason.core.request;



import lombok.Data;

/**
 * UserLoginRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class UserLoginRequest
{
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** デバイスID */
    private String deviceId;
    /** デバイスタイプ */
    private Integer deviceType;
}
