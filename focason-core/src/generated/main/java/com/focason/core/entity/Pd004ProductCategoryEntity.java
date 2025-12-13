/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 商品分類
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "pd004_product_category")
public class Pd004ProductCategoryEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 商品分類コード */
    @Column(name = "product_category_code")
    String productCategoryCode;
    /** 親商品分類コード:レベルが1の場合、自分のカテゴリーコードを格納する。 */
    @Column(name = "parent_product_category_code")
    String parentProductCategoryCode;
    /** 商品分類名称 */
    @Column(name = "product_category_name")
    String productCategoryName;
    /** 商品分類レベル:数字が大きいほど階層が高くなり、最高レベルは1です。 */
    @Column(name = "product_category_level")
    Integer productCategoryLevel;

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
     * Returns the productCategoryCode.
     *
     * @return the productCategoryCode
     */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    /**
     * Sets the productCategoryCode.
     *
     * @param productCategoryCode the productCategoryCode
     */
    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    /**
     * Returns the parentProductCategoryCode.
     *
     * @return the parentProductCategoryCode
     */
    public String getParentProductCategoryCode() {
        return parentProductCategoryCode;
    }

    /**
     * Sets the parentProductCategoryCode.
     *
     * @param parentProductCategoryCode the parentProductCategoryCode
     */
    public void setParentProductCategoryCode(String parentProductCategoryCode) {
        this.parentProductCategoryCode = parentProductCategoryCode;
    }

    /**
     * Returns the productCategoryName.
     *
     * @return the productCategoryName
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    /**
     * Sets the productCategoryName.
     *
     * @param productCategoryName the productCategoryName
     */
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    /**
     * Returns the productCategoryLevel.
     *
     * @return the productCategoryLevel
     */
    public Integer getProductCategoryLevel() {
        return productCategoryLevel;
    }

    /**
     * Sets the productCategoryLevel.
     *
     * @param productCategoryLevel the productCategoryLevel
     */
    public void setProductCategoryLevel(Integer productCategoryLevel) {
        this.productCategoryLevel = productCategoryLevel;
    }
}
