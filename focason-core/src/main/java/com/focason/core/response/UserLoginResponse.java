package com.focason.core.response;

/**
 * UserLoginResponse
 *
 * @param accessToken アクセストークン（認証用トークン）
 * @param expiresIn アクセストークンの有効期間（秒）
 * @param expiresAt アクセストークンの有効期限タイムスタンプ（秒）
 * @param refreshToken リフレッシュトークン（アクセストークンを再発行するためのトークン）
 * @param deviceId デバイスID
 * @param user ユーザー情報
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record UserLoginResponse(String accessToken,Long expiresIn,Long expiresAt,String refreshToken,String deviceId,User user){

/**
 * User
 *
 * @param id 行ID
 * @param uid ユーザー識別子
 * @param username 名前
 * @param email メールアドレス
 */
public record User(Integer id,String uid,String username,String email){}}
