package com.focason.core.request;

/**
 * SendVerificationCodeRequest
 *
 * @param email メールアドレス
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record SendVerificationCodeRequest(String email){}
