package com.focason.core.request;



import lombok.Data;

/**
 * InventoryMovementRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class InventoryMovementRequest
{
    /** 在庫コード */
    private String inventoryCode;
    /** 倉庫コード */
    private String warehouseCode;
    /** ストアコード */
    private String clientCode;
    /** 商品コード */
    private String productSku;
    /** 数量 */
    private Integer quantity;
    /** 移動元:棚ユニットコード */
    private String sourceShelfUnitCode;
    /** 移動先:棚ユニットコード */
    private String destinationShelfUnitCode;
    /** 作業員 */
    private String operator;
    /** 操作タイプ:1:受領上棚, 2:ピッキング移動, 3:補充移動, 4:調達移動, 5:返品処理, 6:在庫整理, 7:品質検査移動 */
    private Integer operationType;
}
