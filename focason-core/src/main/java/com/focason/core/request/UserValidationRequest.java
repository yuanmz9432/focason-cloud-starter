package com.focason.core.request;



import lombok.Data;

/**
 * UserValidationRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class UserValidationRequest
{
    /** メールアドレス */
    private String email;
    /** ユーザー識別子 */
    private String uid;
}
