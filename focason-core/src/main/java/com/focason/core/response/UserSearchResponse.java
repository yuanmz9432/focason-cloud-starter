package com.focason.core.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserSearchResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class UserSearchResponse
{
    /** ユーザー識別子 */
    private String uid;
    /** ユーザー名 */
    private String username;
    /** メールアドレス */
    private String email;
    /** ステータス */
    private Integer status;
}
