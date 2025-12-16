package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AuthResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Builder(toBuilder = true)
@Data
public class AuthResource extends FsResource
{
    /** アクセストークン */
    private String accessToken;
    /** アクセストークン有効期間 */
    private Long expiresIn;
    /** リフレッシュトークン */
    private String refreshToken;
}
