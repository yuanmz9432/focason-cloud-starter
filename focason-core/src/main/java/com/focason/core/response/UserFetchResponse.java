package com.focason.core.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserFetchResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class UserFetchResponse
{
    private Integer id;
    private String uuid;
    private String username;
    private String email;
    private String phone;
    private String companyCode;
    private Integer type;
    private Integer isVerified;
    @Builder.Default
    private String[] warehouseCodes = new String[0];
    @Builder.Default
    private String[] clientCodes = new String[0];
}
