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
    private Integer id;
    private String uuid;
    private String username;
    private String email;
    private String phone;
    private String companyCode;
    private Integer type;
    private Integer isVerified;
}
