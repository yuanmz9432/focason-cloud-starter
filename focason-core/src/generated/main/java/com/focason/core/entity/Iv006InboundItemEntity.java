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
@Table(name = "iv006_inbound_item")
public class Iv006InboundItemEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 入庫部品コード */
    @Column(name = "inbound_item_code")
    String inboundItemCode;
    /** 入庫コード */
    @Column(name = "inbound_code")
    String inboundCode;
    /** 商品SKU */
    @Column(name = "product_sku")
    String productSku;
    /** 予想数量 */
    @Column(name = "expected_quantity")
    Integer expectedQuantity;
    /** 実際数量 */
    @Column(name = "actual_quantity")
    Integer actualQuantity;
    /** 棚ユニットコード */
    @Column(name = "shelf_unit_code")
    String shelfUnitCode;
    /** 入庫部品ステータス:1:未処理, 2:一部入庫, 3:入庫済み, 4:キャンセル, 5:異常 */
    @Column(name = "inbound_item_status")
    Integer inboundItemStatus;
    /** ロット番号 */
    @Column(name = "lot_number")
    String lotNumber;
    /** 製造日 */
    @Column(name = "production_date")
    LocalDate productionDate;

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
     * Returns the inboundItemCode.
     *
     * @return the inboundItemCode
     */
    public String getInboundItemCode() {
        return inboundItemCode;
    }

    /**
     * Sets the inboundItemCode.
     *
     * @param inboundItemCode the inboundItemCode
     */
    public void setInboundItemCode(String inboundItemCode) {
        this.inboundItemCode = inboundItemCode;
    }

    /**
     * Returns the inboundCode.
     *
     * @return the inboundCode
     */
    public String getInboundCode() {
        return inboundCode;
    }

    /**
     * Sets the inboundCode.
     *
     * @param inboundCode the inboundCode
     */
    public void setInboundCode(String inboundCode) {
        this.inboundCode = inboundCode;
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
     * Returns the expectedQuantity.
     *
     * @return the expectedQuantity
     */
    public Integer getExpectedQuantity() {
        return expectedQuantity;
    }

    /**
     * Sets the expectedQuantity.
     *
     * @param expectedQuantity the expectedQuantity
     */
    public void setExpectedQuantity(Integer expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    /**
     * Returns the actualQuantity.
     *
     * @return the actualQuantity
     */
    public Integer getActualQuantity() {
        return actualQuantity;
    }

    /**
     * Sets the actualQuantity.
     *
     * @param actualQuantity the actualQuantity
     */
    public void setActualQuantity(Integer actualQuantity) {
        this.actualQuantity = actualQuantity;
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
     * Returns the inboundItemStatus.
     *
     * @return the inboundItemStatus
     */
    public Integer getInboundItemStatus() {
        return inboundItemStatus;
    }

    /**
     * Sets the inboundItemStatus.
     *
     * @param inboundItemStatus the inboundItemStatus
     */
    public void setInboundItemStatus(Integer inboundItemStatus) {
        this.inboundItemStatus = inboundItemStatus;
    }

    /**
     * Returns the lotNumber.
     *
     * @return the lotNumber
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Sets the lotNumber.
     *
     * @param lotNumber the lotNumber
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * Returns the productionDate.
     *
     * @return the productionDate
     */
    public LocalDate getProductionDate() {
        return productionDate;
    }

    /**
     * Sets the productionDate.
     *
     * @param productionDate the productionDate
     */
    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }
}
