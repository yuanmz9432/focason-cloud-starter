/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 商品マッピング
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg010_product_mapping")
public class Mg010ProductMappingEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 商品SKU */
    @Column(name = "product_sku")
    String productSku;
    /** 外部商品SKU */
    @Column(name = "external_product_sku")
    String externalProductSku;
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
     * Returns the externalProductSku.
     *
     * @return the externalProductSku
     */
    public String getExternalProductSku() {
        return externalProductSku;
    }

    /**
     * Sets the externalProductSku.
     *
     * @param externalProductSku the externalProductSku
     */
    public void setExternalProductSku(String externalProductSku) {
        this.externalProductSku = externalProductSku;
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
