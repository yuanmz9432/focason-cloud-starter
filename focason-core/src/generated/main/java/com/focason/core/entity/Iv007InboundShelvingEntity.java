/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "iv007_inbound_shelving")
public class Iv007InboundShelvingEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 入庫棚上げコード */
    @Column(name = "inbound_shelving_code")
    String inboundShelvingCode;
    /** 入庫部品コード */
    @Column(name = "inbound_item_code")
    String inboundItemCode;
    /** 棚ユニットコード */
    @Column(name = "shelf_unit_code")
    String shelfUnitCode;
    /** 数量 */
    @Column(name = "quantity")
    Integer quantity;

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
     * Returns the inboundShelvingCode.
     *
     * @return the inboundShelvingCode
     */
    public String getInboundShelvingCode() {
        return inboundShelvingCode;
    }

    /**
     * Sets the inboundShelvingCode.
     *
     * @param inboundShelvingCode the inboundShelvingCode
     */
    public void setInboundShelvingCode(String inboundShelvingCode) {
        this.inboundShelvingCode = inboundShelvingCode;
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
}
