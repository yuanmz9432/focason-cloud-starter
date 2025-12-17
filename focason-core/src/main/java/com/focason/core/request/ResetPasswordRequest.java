package com.focason.core.request;

/**
 * ResetPasswordRequest
 *
 * @param uid コード
 * @param password パスワード
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record ResetPasswordRequest(String uid,String password){}
