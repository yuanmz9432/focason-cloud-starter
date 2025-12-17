package com.focason.core.request;

/**
 * UserLoginRequest
 *
 * @param email メールアドレス
 * @param password パスワード
 * @param deviceId デバイスID
 * @param deviceType デバイスタイプ
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record UserLoginRequest(String email,String password,String deviceId,Integer deviceType){}
