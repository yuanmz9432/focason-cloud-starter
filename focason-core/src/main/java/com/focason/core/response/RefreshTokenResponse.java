package com.focason.core.response;

/**
 * RefreshTokenResponse
 *
 * @param accessToken アクセストークン
 * @param expiresIn アクセストークン有効期間
 * @param refreshToken リフレッシュトークン
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record RefreshTokenResponse(String accessToken,Long expiresIn,String refreshToken){}
