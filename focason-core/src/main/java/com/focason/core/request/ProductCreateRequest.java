package com.focason.core.request;



import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * ProductCreateRequest.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ProductCreateRequest
{
    /** 商品SKU */
    private String productSku;
    /** 荷主コード */
    private String clientCode;
    /** 商品名称 */
    private String productName;
    /** 商品ステータス:1:有効, 2:無効, 3:アーカイブ */
    private Integer productStatus;
    /** 商品タイプ:1:通常商品、2:セット商品、3:同梱品 */
    private Integer productType;
    /** 税抜き単価 */
    private BigDecimal includedTaxUnitPrice;
    /** 税込み単価 */
    private BigDecimal excludedTaxUnitPrice;
    /** 税金 */
    private BigDecimal tax;
    /** 税率 */
    private Integer taxRate;
    /** 商品分類コード */
    private String productCategoryCode;
    /** 長さ */
    private BigDecimal length;
    /** 幅 */
    private BigDecimal width;
    /** 高さ */
    private BigDecimal height;
    /** 容積(m³) */
    private BigDecimal volume;
    /** 重量(kg) */
    private BigDecimal weight;
    /** シリアル番号管理要否 */
    private Integer isSerialNumberManaged;
    /** 賞味期間管理要否 */
    private Integer isBestBeforeManaged;
    /** 賞味期間（月数） */
    private Integer bestBeforePeriodDays;
    /** 画像:Amazon S3パス */
    private String image;
    /** 備考 */
    private String remark;
    /** 商品明細リスト */
    public List<SetComponent> setComponents;
    /** 商品オプションリスト */
    public List<ProductOption> productOptions;

    @Data
    public static class SetComponent
    {
        /** 子商品コード */
        private String childProductSku;
        /** 数量 */
        private Integer quantity;
    }

    @Data
    public static class ProductOption
    {
        /** オプションキー */
        private String optionKey;
        /** オプション値 */
        private String optionValue;
    }

}
