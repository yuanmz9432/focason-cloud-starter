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
@Table(name = "pd003_product_option")
public class Pd003ProductOptionEntity extends FsEntity
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
    /** オプションキー */
    @Column(name = "option_key")
    String optionKey;
    /** オプション値 */
    @Column(name = "option_value")
    String optionValue;

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
     * Returns the optionKey.
     *
     * @return the optionKey
     */
    public String getOptionKey() {
        return optionKey;
    }

    /**
     * Sets the optionKey.
     *
     * @param optionKey the optionKey
     */
    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }

    /**
     * Returns the optionValue.
     *
     * @return the optionValue
     */
    public String getOptionValue() {
        return optionValue;
    }

    /**
     * Sets the optionValue.
     *
     * @param optionValue the optionValue
     */
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}
