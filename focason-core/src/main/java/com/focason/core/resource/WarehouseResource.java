package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * WarehouseResource
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
public class WarehouseResource extends FsResource
{
    /**
     * 行ID
     */
    private Integer id;
    /**
     * 倉庫コード
     */
    private String warehouseCode;
    /**
     * 倉庫名称
     */
    private String warehouseName;
    /**
     * 備考
     */
    private String remark;
    /**
     * 会社コード
     */
    private String companyCode;
    /**
     * 倉庫ステータス
     */
    private Integer warehouseStatus;
    /**
     * 関連ストアコード配列
     */
    private String[] clientCodes;
}
