/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 支払方法マッピング
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg012_payment_method_mapping")
public class Mg012PaymentMethodMappingEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 支払方法 */
    @Column(name = "payment_method")
    String paymentMethod;
    /** 外部支払方法 */
    @Column(name = "external_payment_method")
    String externalPaymentMethod;

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
     * Returns the paymentMethod.
     *
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the paymentMethod.
     *
     * @param paymentMethod the paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Returns the externalPaymentMethod.
     *
     * @return the externalPaymentMethod
     */
    public String getExternalPaymentMethod() {
        return externalPaymentMethod;
    }

    /**
     * Sets the externalPaymentMethod.
     *
     * @param externalPaymentMethod the externalPaymentMethod
     */
    public void setExternalPaymentMethod(String externalPaymentMethod) {
        this.externalPaymentMethod = externalPaymentMethod;
    }
}
