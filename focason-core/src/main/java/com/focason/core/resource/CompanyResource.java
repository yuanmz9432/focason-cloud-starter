package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CompanyResource
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
public class CompanyResource extends FsResource
{
    /**
     * 行ID
     */
    private Integer id;
    /**
     * 会社コード
     */
    private String companyCode;
    /**
     * 会社名称
     */
    private String companyName;
    /**
     * 会社ステータス
     */
    private Integer companyStatus;
}
