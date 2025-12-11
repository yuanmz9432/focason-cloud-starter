package com.focason.core.response;



import lombok.Builder;
import lombok.Data;

/**
 * UserLoginResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
public class UserLoginResponse
{
    /** アクセストークン（認証用トークン） */
    private String accessToken;
    /** アクセストークンの有効期間（秒） */
    private Long expiresIn;
    /** アクセストークンの有効期限タイムスタンプ（秒） */
    private Long expiresAt;
    /** リフレッシュトークン（アクセストークンを再発行するためのトークン） */
    private String refreshToken;
    /** デバイスID */
    private String deviceId;
    /** ユーザー情報 */
    private User user;

    @Data
    static public class User
    {
        /** 行ID */
        private Integer id;
        /** ユーザー識別子 */
        private String uid;
        /** 名前 */
        private String username;
        /** メールアドレス */
        private String email;
    }
}
