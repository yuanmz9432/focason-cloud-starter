package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserVerifyResource
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
public class UserVerifyResource extends FsResource
{
    /** ユーザー識別子 */
    private String uid;
    /** 認証トークン */
    private String verificationCode;
}
