package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SupplierResource
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
public class SupplierResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** サプライヤーコード */
    private String supplierCode;
    /** サプライヤー名称 */
    private String supplierName;
    /** 荷主コード */
    private String clientCode;
}
