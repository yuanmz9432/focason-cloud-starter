/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDate;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "iv008_outbound")
public class Iv008OutboundEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 出庫コード */
    @Column(name = "outbound_code")
    String outboundCode;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 倉庫コード */
    @Column(name = "client_code")
    String clientCode;
    /** 商品SKU */
    @Column(name = "product_sku")
    String productSku;
    /** 数量 */
    @Column(name = "quantity")
    Integer quantity;
    /** 棚ユニットコード */
    @Column(name = "shelf_unit_code")
    String shelfUnitCode;
    /** 出庫日付 */
    @Column(name = "outbound_date")
    LocalDate outboundDate;
    /** 出庫ステータス:1:未処理, 2:処理中, 3:完了, 4:キャンセル, 5:異常 */
    @Column(name = "outbound_status")
    Integer outboundStatus;
    /** 出庫タイプ:1:正規出庫, 2:返品, 3:サンプル出庫, 4:破棄, 5:移動 */
    @Column(name = "outbound_type")
    Integer outboundType;
    /** 出庫先 */
    @Column(name = "destination")
    String destination;
    /** 担当者 */
    @Column(name = "manager")
    String manager;
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
     * Returns the outboundCode.
     *
     * @return the outboundCode
     */
    public String getOutboundCode() {
        return outboundCode;
    }

    /**
     * Sets the outboundCode.
     *
     * @param outboundCode the outboundCode
     */
    public void setOutboundCode(String outboundCode) {
        this.outboundCode = outboundCode;
    }

    /**
     * Returns the warehouseCode.
     *
     * @return the warehouseCode
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * Sets the warehouseCode.
     *
     * @param warehouseCode the warehouseCode
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
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
     * Returns the shelfUnitCode.
     *
     * @return the shelfUnitCode
     */
    public String getShelfUnitCode() {
        return shelfUnitCode;
    }

    /**
     * Sets the shelfUnitCode.
     *
     * @param shelfUnitCode the shelfUnitCode
     */
    public void setShelfUnitCode(String shelfUnitCode) {
        this.shelfUnitCode = shelfUnitCode;
    }

    /**
     * Returns the outboundDate.
     *
     * @return the outboundDate
     */
    public LocalDate getOutboundDate() {
        return outboundDate;
    }

    /**
     * Sets the outboundDate.
     *
     * @param outboundDate the outboundDate
     */
    public void setOutboundDate(LocalDate outboundDate) {
        this.outboundDate = outboundDate;
    }

    /**
     * Returns the outboundStatus.
     *
     * @return the outboundStatus
     */
    public Integer getOutboundStatus() {
        return outboundStatus;
    }

    /**
     * Sets the outboundStatus.
     *
     * @param outboundStatus the outboundStatus
     */
    public void setOutboundStatus(Integer outboundStatus) {
        this.outboundStatus = outboundStatus;
    }

    /**
     * Returns the outboundType.
     *
     * @return the outboundType
     */
    public Integer getOutboundType() {
        return outboundType;
    }

    /**
     * Sets the outboundType.
     *
     * @param outboundType the outboundType
     */
    public void setOutboundType(Integer outboundType) {
        this.outboundType = outboundType;
    }

    /**
     * Returns the destination.
     *
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination.
     *
     * @param destination the destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns the manager.
     *
     * @return the manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * Sets the manager.
     *
     * @param manager the manager
     */
    public void setManager(String manager) {
        this.manager = manager;
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
