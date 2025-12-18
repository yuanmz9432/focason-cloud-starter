/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.math.BigDecimal;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "vw003_product_search")
public class Vw003ProductSearchEntity extends FsEntity
{
    /** 行ID */
    @Column(name = "id")
    Integer id;
    /** 商品SKU */
    @Column(name = "product_sku")
    String productSku;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 荷主名称 */
    @Column(name = "client_name")
    String clientName;
    /** 商品名称 */
    @Column(name = "product_name")
    String productName;
    /** 商品タイプ:1:通常商品、2:セット商品、3:同梱品 */
    @Column(name = "product_type")
    Integer productType;
    /** 税抜き単価 */
    @Column(name = "included_tax_unit_price")
    BigDecimal includedTaxUnitPrice;
    /** 税込み単価 */
    @Column(name = "excluded_tax_unit_price")
    BigDecimal excludedTaxUnitPrice;
    /** 税金 */
    @Column(name = "tax")
    BigDecimal tax;
    /** 税率 */
    @Column(name = "tax_rate")
    Integer taxRate;
    /** 商品分類コード */
    @Column(name = "product_category_code")
    String productCategoryCode;
    /** 長さ */
    @Column(name = "length")
    BigDecimal length;
    /** 幅 */
    @Column(name = "width")
    BigDecimal width;
    /** 高さ */
    @Column(name = "height")
    BigDecimal height;
    /** 容積(m³) */
    @Column(name = "volume")
    BigDecimal volume;
    /** 重量(kg) */
    @Column(name = "weight")
    BigDecimal weight;
    /** シリアル番号管理要否 */
    @Column(name = "is_serial_number_managed")
    Integer isSerialNumberManaged;
    /** 賞味期間管理要否 */
    @Column(name = "is_best_before_managed")
    Integer isBestBeforeManaged;
    /** 賞味期間（日数） */
    @Column(name = "best_before_period_days")
    Integer bestBeforePeriodDays;
    /** 商品ステータス:1:有効, 2:無効, 3:アーカイブ */
    @Column(name = "product_status")
    Integer productStatus;
    /** 画像:Amazon S3パス */
    @Column(name = "image")
    String image;
    /** バーコード:Amazon S3パス */
    @Column(name = "barcode")
    String barcode;
    /** QRコード:Amazon S3パス */
    @Column(name = "qr_code")
    String qrCode;
    /** 備考 */
    @Column(name = "remark")
    String remark;

    /**
     * Returns the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the productSku.
     *
     * @return the productSku
     */
    public String getProductSku() {
        return productSku;
    }

    /**
     * Sets the productSku.
     *
     * @param productSku the productSku
     */
    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    /**
     * Returns the clientCode.
     *
     * @return the clientCode
     */
    public String getClientCode() {
        return clientCode;
    }

    /**
     * Sets the clientCode.
     *
     * @param clientCode the clientCode
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    /**
     * Returns the clientName.
     *
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the clientName.
     *
     * @param clientName the clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Returns the productName.
     *
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the productName.
     *
     * @param productName the productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the productType.
     *
     * @return the productType
     */
    public Integer getProductType() {
        return productType;
    }

    /**
     * Sets the productType.
     *
     * @param productType the productType
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    /**
     * Returns the includedTaxUnitPrice.
     *
     * @return the includedTaxUnitPrice
     */
    public BigDecimal getIncludedTaxUnitPrice() {
        return includedTaxUnitPrice;
    }

    /**
     * Sets the includedTaxUnitPrice.
     *
     * @param includedTaxUnitPrice the includedTaxUnitPrice
     */
    public void setIncludedTaxUnitPrice(BigDecimal includedTaxUnitPrice) {
        this.includedTaxUnitPrice = includedTaxUnitPrice;
    }

    /**
     * Returns the excludedTaxUnitPrice.
     *
     * @return the excludedTaxUnitPrice
     */
    public BigDecimal getExcludedTaxUnitPrice() {
        return excludedTaxUnitPrice;
    }

    /**
     * Sets the excludedTaxUnitPrice.
     *
     * @param excludedTaxUnitPrice the excludedTaxUnitPrice
     */
    public void setExcludedTaxUnitPrice(BigDecimal excludedTaxUnitPrice) {
        this.excludedTaxUnitPrice = excludedTaxUnitPrice;
    }

    /**
     * Returns the tax.
     *
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * Sets the tax.
     *
     * @param tax the tax
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * Returns the taxRate.
     *
     * @return the taxRate
     */
    public Integer getTaxRate() {
        return taxRate;
    }

    /**
     * Sets the taxRate.
     *
     * @param taxRate the taxRate
     */
    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * Returns the productCategoryCode.
     *
     * @return the productCategoryCode
     */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    /**
     * Sets the productCategoryCode.
     *
     * @param productCategoryCode the productCategoryCode
     */
    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    /**
     * Returns the length.
     *
     * @return the length
     */
    public BigDecimal getLength() {
        return length;
    }

    /**
     * Sets the length.
     *
     * @param length the length
     */
    public void setLength(BigDecimal length) {
        this.length = length;
    }

    /**
     * Returns the width.
     *
     * @return the width
     */
    public BigDecimal getWidth() {
        return width;
    }

    /**
     * Sets the width.
     *
     * @param width the width
     */
    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    /**
     * Returns the height.
     *
     * @return the height
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Sets the height.
     *
     * @param height the height
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    /**
     * Returns the volume.
     *
     * @return the volume
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * Sets the volume.
     *
     * @param volume the volume
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * Returns the weight.
     *
     * @return the weight
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the weight.
     *
     * @param weight the weight
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * Returns the isSerialNumberManaged.
     *
     * @return the isSerialNumberManaged
     */
    public Integer getIsSerialNumberManaged() {
        return isSerialNumberManaged;
    }

    /**
     * Sets the isSerialNumberManaged.
     *
     * @param isSerialNumberManaged the isSerialNumberManaged
     */
    public void setIsSerialNumberManaged(Integer isSerialNumberManaged) {
        this.isSerialNumberManaged = isSerialNumberManaged;
    }

    /**
     * Returns the isBestBeforeManaged.
     *
     * @return the isBestBeforeManaged
     */
    public Integer getIsBestBeforeManaged() {
        return isBestBeforeManaged;
    }

    /**
     * Sets the isBestBeforeManaged.
     *
     * @param isBestBeforeManaged the isBestBeforeManaged
     */
    public void setIsBestBeforeManaged(Integer isBestBeforeManaged) {
        this.isBestBeforeManaged = isBestBeforeManaged;
    }

    /**
     * Returns the bestBeforePeriodDays.
     *
     * @return the bestBeforePeriodDays
     */
    public Integer getBestBeforePeriodDays() {
        return bestBeforePeriodDays;
    }

    /**
     * Sets the bestBeforePeriodDays.
     *
     * @param bestBeforePeriodDays the bestBeforePeriodDays
     */
    public void setBestBeforePeriodDays(Integer bestBeforePeriodDays) {
        this.bestBeforePeriodDays = bestBeforePeriodDays;
    }

    /**
     * Returns the productStatus.
     *
     * @return the productStatus
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * Sets the productStatus.
     *
     * @param productStatus the productStatus
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * Returns the image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the barcode.
     *
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Sets the barcode.
     *
     * @param barcode the barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * Returns the qrCode.
     *
     * @return the qrCode
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Sets the qrCode.
     *
     * @param qrCode the qrCode
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * Returns the remark.
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the remark.
     *
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
