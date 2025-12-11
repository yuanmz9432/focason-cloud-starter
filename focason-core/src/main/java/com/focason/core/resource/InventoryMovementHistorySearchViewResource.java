package com.focason.core.resource;



import java.time.LocalDateTime;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * InventorySearchViewResource
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
public class InventoryMovementHistorySearchViewResource
{
    /** 行ID */
    private Integer id;
    /** 在庫コード */
    private String inventoryCode;
    /** 倉庫コード */
    private String warehouseCode;
    /** 倉庫名称 */
    private String warehouseName;
    /** ストアコード */
    private String clientCode;
    /** ストア名称 */
    private String storeName;
    /** 商品コード */
    private String productCode;
    /** 商品名称 */
    private String productName;
    /** 商品タイプ:1:通常商品、2:セット商品、3:同梱品 */
    private Integer productType;
    /** 数量 */
    private Integer quantity;
    /** 棚コード */
    private String shelfCode;
    /** 棚名称 */
    private String shelfName;
    /** 移動元:棚ユニットコード */
    private String sourceShelfUnitCode;
    /** 棚ユニット名称 */
    private String sourceShelfUnitName;
    /** 移動先:棚ユニットコード */
    private String destinationShelfUnitCode;
    /** 棚ユニット名称 */
    private String destinationShelfUnitName;
    /** 作業員 */
    private String operator;
    /** 操作日時 */
    private LocalDateTime operationDatetime;
    /** 操作タイプ:1:倉庫間移動, 2:倉庫内移動 */
    private Integer operationType;
    /** 備考 */
    private String remark;
}
