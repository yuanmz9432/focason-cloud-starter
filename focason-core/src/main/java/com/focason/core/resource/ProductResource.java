package com.focason.core.resource;



import com.focason.core.annotation.TargetElementType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProductResource.
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
public class ProductResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** 商品SKU */
    private String productSku;
    /** 荷主コード */
    private String clientCode;
    /** 商品名称 */
    private String productName;
    /** 商品タイプ:1:通常商品、2:セット商品、3:同梱品 */
    private Integer productType;
    /** 税込み単価 */
    private BigDecimal includedTaxUnitPrice;
    /** 税抜き単価 */
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
    /** 商品ステータス:1:有効, 2:無効, 3:アーカイブ */
    private Integer productStatus;
    /** 画像:Amazon S3パス */
    private String image;
    /** バーコード:Amazon S3パス */
    private String barcode;
    /** QRコード:Amazon S3パス */
    private String qrCode;
    /** 備考 */
    private String remark;
    /** 構成品リスト */
    @TargetElementType(SetComponentResource.class)
    @Builder.Default
    public List<SetComponentResource> setComponents = new ArrayList<>();
    /** 商品オプションリスト */
    @TargetElementType(ProductOptionResource.class)
    @Builder.Default
    public List<ProductOptionResource> productOptions = new ArrayList<>();

}
