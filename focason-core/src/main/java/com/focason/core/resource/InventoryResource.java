package com.focason.core.resource;



import java.time.LocalDateTime;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * InventoryResource
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
public class InventoryResource extends FsResource
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
    private String productCode;
    /** 棚ユニットコード */
    private String shelvingUnitCode;
    /** 実在庫数量:物理的にカウントされた在庫数 */
    private Integer actualStock;
    /** 利用可能在庫数量:顧客や顧客注文に対して利用可能な在庫数 */
    private Integer availableStock;
    /** 予約在庫数量:顧客が商品を注文したが、まだ出荷されていない在庫数 */
    private Integer reservedStock;
    /** バックオーダー在庫数量:現在は在庫切れであり、注文された商品の数量 */
    private Integer backOrderStock;
    /** リードタイム在庫数量:供給者からの補充注文が到着するまでの間の在庫数 */
    private Integer leadTimeStock;
    /** 賞味期限 */
    private LocalDateTime expiryDate;
    /** 在庫ステータス:1:未検査, 2:検査合格, 3:検査不合格 */
    private Integer inventoryStatus;
    /** ロット番号 */
    private String lotNumber;
    /** 備考 */
    private String remark;
}
