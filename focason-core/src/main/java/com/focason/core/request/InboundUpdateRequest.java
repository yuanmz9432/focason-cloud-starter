package com.focason.core.request;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * InboundUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class InboundUpdateRequest
{
    /** サプライヤーコード */
    private String supplierCode;
    /** 入庫依頼日時 */
    private LocalDate requestInboundDate;
    /** 予想入庫日時 */
    private LocalDate expectedInboundDate;
    /** 実際入庫日時 */
    private LocalDate actualInboundDate;
    /** 総額 */
    private BigDecimal totalPrice;
    /** 入庫ステータス:1:未処理, 2:一部入庫, 3:入庫済み, 4:キャンセル, 5:異常 */
    private Integer inboundStatus;
    /** 入庫タイプ:1:通常入庫, 2:返品入庫, 3:調達入庫, 4:棚卸差異調整入庫 */
    private Integer inboundType;
    /** 配送伝票番号 */
    private String shipmentManifestNumber;
    /** 店舗側担当者 */
    private String storeManager;
    /** 倉庫側担当者 */
    private String warehouseManager;
    /** 備考 */
    private String remark;
    /** 入庫詳細リスト */
    public List<InboundItem> inboundItems = new ArrayList<>();

    @Data
    public static class InboundItem
    {
        /** 商品コード */
        private String productCode;
        /** 予想数量 */
        private Integer expectedQuantity;
        /** 単価 */
        private BigDecimal unitPrice;
    }
}
