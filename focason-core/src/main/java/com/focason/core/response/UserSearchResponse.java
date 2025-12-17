package com.focason.core.response;

/**
 * UserSearchResponse
 *
 * @param uid ユーザー識別子
 * @param username ユーザー名
 * @param email メールアドレス
 * @param status ステータス
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record UserSearchResponse(String uid,String username,String email,Integer status){}
