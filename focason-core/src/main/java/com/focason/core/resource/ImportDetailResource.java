package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ImportDetailResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ImportDetailResource
{
    /** アクセストークン */
    private String accessToken;
    /** アクセストークン有効期間 */
    private Long expiresIn;
    /** リフレッシュトークン */
    private String refreshToken;
}
