package com.focason.core.request;



import lombok.Data;

/**
 * UserVerifyRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class UserVerifyRequest
{
    /** ユーザー識別子 */
    private String uid;
    /** 認証コード */
    private String verificationCode;
}
