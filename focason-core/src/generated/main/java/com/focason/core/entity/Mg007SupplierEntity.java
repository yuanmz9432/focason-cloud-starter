/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "mg007_supplier")
public class Mg007SupplierEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** サプライヤーコード */
    @Column(name = "supplier_code")
    String supplierCode;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** サプライヤー名称 */
    @Column(name = "supplier_name")
    String supplierName;

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
     * Returns the supplierCode.
     *
     * @return the supplierCode
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Sets the supplierCode.
     *
     * @param supplierCode the supplierCode
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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
     * Returns the supplierName.
     *
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Sets the supplierName.
     *
     * @param supplierName the supplierName
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
