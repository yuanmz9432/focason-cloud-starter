package com.focason.core.resource;



import java.time.LocalDateTime;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserVerificationResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class UserVerificationResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** ユーザー識別子 */
    private String uid;
    /** 認証コード */
    private String verificationCode;
    /** 認証期限 */
    private LocalDateTime expiresAt;
    /** 認証日時 */
    private LocalDateTime verifiedAt;
}
