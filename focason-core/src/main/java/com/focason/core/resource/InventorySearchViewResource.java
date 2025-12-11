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
public class InventorySearchViewResource
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
    /** 棚コード */
    private String shelfCode;
    /** 棚名称 */
    private String shelfName;
    /** 棚ユニットコード */
    private String shelfUnitCode;
    /** 棚ユニット名称 */
    private String shelfUnitName;
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
    /** 在庫ステータス:1:未検査, 2:検査合格, 3:検査不合格 */
    private Integer inventoryStatus;
    /** 賞味期限 */
    private LocalDateTime expiryDate;
    /** 備考 */
    private String remark;
}
