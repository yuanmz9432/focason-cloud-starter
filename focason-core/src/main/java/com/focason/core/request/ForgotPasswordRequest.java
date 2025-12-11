package com.focason.core.request;



import lombok.Data;

/**
 * ForgotPasswordRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ForgotPasswordRequest
{
    /** メールアドレス */
    private String email;
}
