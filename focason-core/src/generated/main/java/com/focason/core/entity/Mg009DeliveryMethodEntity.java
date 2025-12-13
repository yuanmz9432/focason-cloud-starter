/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 配送方法
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg009_delivery_method")
public class Mg009DeliveryMethodEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 配送方法コード */
    @Column(name = "delivery_method_code")
    String deliveryMethodCode;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 配送会社 */
    @Column(name = "delivery_company")
    String deliveryCompany;
    /** 配送方法 */
    @Column(name = "delivery_method")
    String deliveryMethod;
    /** 配送時間帯 */
    @Column(name = "delivery_time_slot")
    String deliveryTimeSlot;

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
     * Returns the deliveryMethodCode.
     *
     * @return the deliveryMethodCode
     */
    public String getDeliveryMethodCode() {
        return deliveryMethodCode;
    }

    /**
     * Sets the deliveryMethodCode.
     *
     * @param deliveryMethodCode the deliveryMethodCode
     */
    public void setDeliveryMethodCode(String deliveryMethodCode) {
        this.deliveryMethodCode = deliveryMethodCode;
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
     * Returns the deliveryCompany.
     *
     * @return the deliveryCompany
     */
    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    /**
     * Sets the deliveryCompany.
     *
     * @param deliveryCompany the deliveryCompany
     */
    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    /**
     * Returns the deliveryMethod.
     *
     * @return the deliveryMethod
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Sets the deliveryMethod.
     *
     * @param deliveryMethod the deliveryMethod
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    /**
     * Returns the deliveryTimeSlot.
     *
     * @return the deliveryTimeSlot
     */
    public String getDeliveryTimeSlot() {
        return deliveryTimeSlot;
    }

    /**
     * Sets the deliveryTimeSlot.
     *
     * @param deliveryTimeSlot the deliveryTimeSlot
     */
    public void setDeliveryTimeSlot(String deliveryTimeSlot) {
        this.deliveryTimeSlot = deliveryTimeSlot;
    }
}
