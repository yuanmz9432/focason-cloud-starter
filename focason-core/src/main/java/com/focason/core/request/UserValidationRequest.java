package com.focason.core.request;

/**
 * UserValidationRequest
 *
 * @param email メールアドレス
 * @param uid ユーザー識別子
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record UserValidationRequest(String email,String uid){}
