package com.focason.core.request;



import lombok.Data;

/**
 * CompanyUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class CompanyUpdateRequest
{

    /**
     * 会社名称
     */
    private String companyName;

    /**
     * ステータス
     */
    private Integer companyStatus;
}
