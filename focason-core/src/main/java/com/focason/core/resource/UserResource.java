package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class UserResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** ユーザー識別子 */
    private String uid;
    /** 名前 */
    private String username;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** ステータス(0:無効, 1:有効) */
    private Integer status;
    /** デバイスID */
    private String deviceId;
    /** デバイスタイプ */
    private Integer deviceType;
    /** 認証コード */
    private String verificationCode;
}
