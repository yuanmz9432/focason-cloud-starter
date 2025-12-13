/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 棚ユニット
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "iv003_shelf_unit")
public class Iv003ShelfUnitEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 棚ユニットコード */
    @Column(name = "shelf_unit_code")
    String shelfUnitCode;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** ゾーンコード */
    @Column(name = "zone_code")
    String zoneCode;
    /** 棚コード */
    @Column(name = "shelf_code")
    String shelfCode;
    /** 棚ユニット名称 */
    @Column(name = "shelf_unit_name")
    String shelfUnitName;
    /** 備考 */
    @Column(name = "remark")
    String remark;

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
     * Returns the zoneCode.
     *
     * @return the zoneCode
     */
    public String getZoneCode() {
        return zoneCode;
    }

    /**
     * Sets the zoneCode.
     *
     * @param zoneCode the zoneCode
     */
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    /**
     * Returns the shelfCode.
     *
     * @return the shelfCode
     */
    public String getShelfCode() {
        return shelfCode;
    }

    /**
     * Sets the shelfCode.
     *
     * @param shelfCode the shelfCode
     */
    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    /**
     * Returns the shelfUnitName.
     *
     * @return the shelfUnitName
     */
    public String getShelfUnitName() {
        return shelfUnitName;
    }

    /**
     * Sets the shelfUnitName.
     *
     * @param shelfUnitName the shelfUnitName
     */
    public void setShelfUnitName(String shelfUnitName) {
        this.shelfUnitName = shelfUnitName;
    }

    /**
     * Returns the remark.
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the remark.
     *
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
