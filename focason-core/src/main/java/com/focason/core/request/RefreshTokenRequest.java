package com.focason.core.request;

/**
 * RefreshTokenRequest
 *
 * @param uid ユーザー識別子
 * @param deviceId デバイスID
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record RefreshTokenRequest(String uid,String deviceId){}
