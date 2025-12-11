package com.focason.core.request;



import lombok.Data;

/**
 * SendVerificationCodeRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class SendVerificationCodeRequest
{
    /** メールアドレス */
    private String email;
}
