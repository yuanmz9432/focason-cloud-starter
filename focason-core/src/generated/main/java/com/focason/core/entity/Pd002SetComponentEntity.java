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
@Table(name = "pd002_set_component")
public class Pd002SetComponentEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 親商品SKU */
    @Column(name = "parent_product_sku")
    String parentProductSku;
    /** 子商品SKU */
    @Column(name = "child_product_sku")
    String childProductSku;
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
     * Returns the parentProductSku.
     *
     * @return the parentProductSku
     */
    public String getParentProductSku() {
        return parentProductSku;
    }

    /**
     * Sets the parentProductSku.
     *
     * @param parentProductSku the parentProductSku
     */
    public void setParentProductSku(String parentProductSku) {
        this.parentProductSku = parentProductSku;
    }

    /**
     * Returns the childProductSku.
     *
     * @return the childProductSku
     */
    public String getChildProductSku() {
        return childProductSku;
    }

    /**
     * Sets the childProductSku.
     *
     * @param childProductSku the childProductSku
     */
    public void setChildProductSku(String childProductSku) {
        this.childProductSku = childProductSku;
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
