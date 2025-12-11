package com.focason.core.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CompanyFetchResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class CompanyFetchResponse
{
    /** 行ID */
    private Integer id;
    /** 会社コード */
    private String companyCode;
    /** 会社名称 */
    private String companyName;
    /** 会社ステータス */
    private Integer companyStatus;
}
