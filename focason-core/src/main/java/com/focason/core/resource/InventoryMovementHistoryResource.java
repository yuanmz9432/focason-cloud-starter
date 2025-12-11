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
public class InventoryMovementHistoryResource
{
    /** 行ID */
    private Integer id;
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
    /** 操作日時 */
    private LocalDateTime operationDatetime;
    /** 操作タイプ:1:受領上棚, 2:ピッキング移動, 3:補充移動, 4:調達移動, 5:返品処理, 6:在庫整理, 7:品質検査移動 */
    private Integer operationType;
    /** 備考 */
    private String remark;
}
