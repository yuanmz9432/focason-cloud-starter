package com.focason.core.request;

/**
 * UserCreateRequest
 *
 * @param uid ユーザー識別子
 * @param username ユーザー名
 * @param email メールアドレス
 * @param password パスワード
 * @param status ステータス
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record UserCreateRequest(String uid,String username,String email,String password,Integer status){}
