/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 倉庫
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg002_warehouse")
public class Mg002WarehouseEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 会社コード */
    @Column(name = "company_code")
    String companyCode;
    /** 倉庫名称 */
    @Column(name = "warehouse_name")
    String warehouseName;
    /** 倉庫ステータス:1:稼働中, 2:非稼働中, 3:閉鎖中 */
    @Column(name = "warehouse_status")
    Integer warehouseStatus;

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
     * Returns the companyCode.
     *
     * @return the companyCode
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * Sets the companyCode.
     *
     * @param companyCode the companyCode
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * Returns the warehouseName.
     *
     * @return the warehouseName
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Sets the warehouseName.
     *
     * @param warehouseName the warehouseName
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * Returns the warehouseStatus.
     *
     * @return the warehouseStatus
     */
    public Integer getWarehouseStatus() {
        return warehouseStatus;
    }

    /**
     * Sets the warehouseStatus.
     *
     * @param warehouseStatus the warehouseStatus
     */
    public void setWarehouseStatus(Integer warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }
}
