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
@Table(name = "od002_order_detail")
public class Od002OrderDetailEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 受注コード */
    @Column(name = "order_code")
    String orderCode;
    /** 商品SKU */
    @Column(name = "product_sku")
    String productSku;
    /** 商品名称 */
    @Column(name = "product_name")
    String productName;
    /** 数量 */
    @Column(name = "quantity")
    Integer quantity;
    /** 税抜き単価 */
    @Column(name = "excluded_unit_price")
    BigDecimal excludedUnitPrice;
    /** 商品の小計金額 */
    @Column(name = "subtotal_amount")
    BigDecimal subtotalAmount;
    /** 税金 */
    @Column(name = "tax")
    BigDecimal tax;
    /** 商品の合計金額 */
    @Column(name = "total_product_amount")
    BigDecimal totalProductAmount;
    /** 割引金額 */
    @Column(name = "discount_amount")
    BigDecimal discountAmount;
    /** 調整金額 */
    @Column(name = "adjustment_amount")
    BigDecimal adjustmentAmount;
    /** 総金額 */
    @Column(name = "total_amount")
    BigDecimal totalAmount;

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
     * Returns the orderCode.
     *
     * @return the orderCode
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * Sets the orderCode.
     *
     * @param orderCode the orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
     * Returns the quantity.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the excludedUnitPrice.
     *
     * @return the excludedUnitPrice
     */
    public BigDecimal getExcludedUnitPrice() {
        return excludedUnitPrice;
    }

    /**
     * Sets the excludedUnitPrice.
     *
     * @param excludedUnitPrice the excludedUnitPrice
     */
    public void setExcludedUnitPrice(BigDecimal excludedUnitPrice) {
        this.excludedUnitPrice = excludedUnitPrice;
    }

    /**
     * Returns the subtotalAmount.
     *
     * @return the subtotalAmount
     */
    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    /**
     * Sets the subtotalAmount.
     *
     * @param subtotalAmount the subtotalAmount
     */
    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
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
     * Returns the totalProductAmount.
     *
     * @return the totalProductAmount
     */
    public BigDecimal getTotalProductAmount() {
        return totalProductAmount;
    }

    /**
     * Sets the totalProductAmount.
     *
     * @param totalProductAmount the totalProductAmount
     */
    public void setTotalProductAmount(BigDecimal totalProductAmount) {
        this.totalProductAmount = totalProductAmount;
    }

    /**
     * Returns the discountAmount.
     *
     * @return the discountAmount
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the discountAmount.
     *
     * @param discountAmount the discountAmount
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Returns the adjustmentAmount.
     *
     * @return the adjustmentAmount
     */
    public BigDecimal getAdjustmentAmount() {
        return adjustmentAmount;
    }

    /**
     * Sets the adjustmentAmount.
     *
     * @param adjustmentAmount the adjustmentAmount
     */
    public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    /**
     * Returns the totalAmount.
     *
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the totalAmount.
     *
     * @param totalAmount the totalAmount
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
