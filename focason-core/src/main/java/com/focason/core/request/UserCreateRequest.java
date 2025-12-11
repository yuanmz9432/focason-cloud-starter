package com.focason.core.request;



import lombok.Data;

/**
 * UserCreateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class UserCreateRequest
{
    /** ユーザー名 */
    private String username;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** ステータス:1:通常ユーザー、2:ルートユーザー */
    private Integer type;
    /** 会社コード */
    private String companyCode;
    /** 部門コード配列 */
    private String[] warehouseCodes;
    private String[] clientCodes;
}
