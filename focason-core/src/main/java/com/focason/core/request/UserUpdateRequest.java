package com.focason.core.request;



import lombok.Data;

/**
 * UserUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class UserUpdateRequest
{
    /** ユーザー識別子 */
    private String uid;
    /** ユーザー名 */
    private String username;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** ステータス */
    private Integer status;

}
