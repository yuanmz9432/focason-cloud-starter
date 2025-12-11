package com.focason.core.request;



import lombok.Data;

/**
 * UserRegisterRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class UserRegisterRequest
{
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** 認証コード */
    private String verificationCode;
}
