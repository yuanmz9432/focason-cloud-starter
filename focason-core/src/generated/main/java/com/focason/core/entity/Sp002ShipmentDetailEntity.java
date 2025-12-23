/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
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
@Table(name = "sp002_shipment_detail")
public class Sp002ShipmentDetailEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 出荷コード */
    @Column(name = "shipment_code")
    String shipmentCode;
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
    /** 商品の小計金額:商品の小計金額=税抜き単価*数量 */
    @Column(name = "subtotal_price")
    BigDecimal subtotalPrice;
    /** 税金 */
    @Column(name = "tax")
    BigDecimal tax;
    /** 商品の合計金額:商品の合計金額=商品の小計金額+税金 */
    @Column(name = "total_price")
    BigDecimal totalPrice;
    /** 商品の割引金額 */
    @Column(name = "discount_amount")
    BigDecimal discountAmount;
    /** 商品の総金額:総金額=商品の合計金額-商品の割引金額 */
    @Column(name = "total_amount")
    BigDecimal totalAmount;
    /** 引当済み数量 */
    @Column(name = "allocated_amount")
    Integer allocatedAmount;
    /** 引当ステータス:1:未処理, 2:一部引当済み, 3:引当済み */
    @Column(name = "allocation_status")
    Integer allocationStatus;

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
     * Returns the shipmentCode.
     *
     * @return the shipmentCode
     */
    public String getShipmentCode() {
        return shipmentCode;
    }

    /**
     * Sets the shipmentCode.
     *
     * @param shipmentCode the shipmentCode
     */
    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
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
     * Returns the subtotalPrice.
     *
     * @return the subtotalPrice
     */
    public BigDecimal getSubtotalPrice() {
        return subtotalPrice;
    }

    /**
     * Sets the subtotalPrice.
     *
     * @param subtotalPrice the subtotalPrice
     */
    public void setSubtotalPrice(BigDecimal subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
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
     * Returns the totalPrice.
     *
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the totalPrice.
     *
     * @param totalPrice the totalPrice
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    /**
     * Returns the allocatedAmount.
     *
     * @return the allocatedAmount
     */
    public Integer getAllocatedAmount() {
        return allocatedAmount;
    }

    /**
     * Sets the allocatedAmount.
     *
     * @param allocatedAmount the allocatedAmount
     */
    public void setAllocatedAmount(Integer allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }

    /**
     * Returns the allocationStatus.
     *
     * @return the allocationStatus
     */
    public Integer getAllocationStatus() {
        return allocationStatus;
    }

    /**
     * Sets the allocationStatus.
     *
     * @param allocationStatus the allocationStatus
     */
    public void setAllocationStatus(Integer allocationStatus) {
        this.allocationStatus = allocationStatus;
    }
}
