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
    /** ユーザー名 */
    private String username;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** ステータス:1:通常ユーザー、2:ルートユーザー */
    private Integer type;
    /** 部門コード配列 */
    private String[] warehouseCodes;
    private String[] clientCodes;

}
