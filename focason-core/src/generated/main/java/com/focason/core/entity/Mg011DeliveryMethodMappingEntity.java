/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 配送方法マッピング
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg011_delivery_method_mapping")
public class Mg011DeliveryMethodMappingEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 配送方法コード */
    @Column(name = "delivery_method_code")
    String deliveryMethodCode;
    /** 外部配送方法 */
    @Column(name = "external_delivery_method")
    String externalDeliveryMethod;
    /** 外部配送時間帯 */
    @Column(name = "external_delivery_time_slot")
    String externalDeliveryTimeSlot;
    /** 外部システム */
    @Column(name = "external_system")
    String externalSystem;

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
     * Returns the externalDeliveryMethod.
     *
     * @return the externalDeliveryMethod
     */
    public String getExternalDeliveryMethod() {
        return externalDeliveryMethod;
    }

    /**
     * Sets the externalDeliveryMethod.
     *
     * @param externalDeliveryMethod the externalDeliveryMethod
     */
    public void setExternalDeliveryMethod(String externalDeliveryMethod) {
        this.externalDeliveryMethod = externalDeliveryMethod;
    }

    /**
     * Returns the externalDeliveryTimeSlot.
     *
     * @return the externalDeliveryTimeSlot
     */
    public String getExternalDeliveryTimeSlot() {
        return externalDeliveryTimeSlot;
    }

    /**
     * Sets the externalDeliveryTimeSlot.
     *
     * @param externalDeliveryTimeSlot the externalDeliveryTimeSlot
     */
    public void setExternalDeliveryTimeSlot(String externalDeliveryTimeSlot) {
        this.externalDeliveryTimeSlot = externalDeliveryTimeSlot;
    }

    /**
     * Returns the externalSystem.
     *
     * @return the externalSystem
     */
    public String getExternalSystem() {
        return externalSystem;
    }

    /**
     * Sets the externalSystem.
     *
     * @param externalSystem the externalSystem
     */
    public void setExternalSystem(String externalSystem) {
        this.externalSystem = externalSystem;
    }
}
