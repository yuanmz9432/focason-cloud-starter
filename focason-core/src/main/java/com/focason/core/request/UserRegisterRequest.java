package com.focason.core.request;

/**
 * UserRegisterRequest
 *
 * @param email メールアドレス
 * @param password パスワード
 * @param verificationCode 認証コード
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record UserRegisterRequest(String email,String password,String verificationCode){}
