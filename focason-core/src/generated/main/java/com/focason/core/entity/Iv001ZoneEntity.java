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
@Table(name = "iv001_zone")
public class Iv001ZoneEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** ゾーンコード */
    @Column(name = "zone_code")
    String zoneCode;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** ゾーン名称 */
    @Column(name = "zone_name")
    String zoneName;
    /**
     * ゾーンタイプ:10:受入ゾーン, 11:検品ゾーン, 12:一時保管ゾーン, 20:棚保管ゾーン, 21:バルク保管ゾーン, 22:冷蔵ゾーン, 23:危険物保管ゾーン, 24:高価値商品ゾーン, 25:返品保管ゾーン,
     * 30:ピッキングゾーン, 31:仕分けゾーン, 40:梱包ゾーン, 41:ラベリングゾーン, 42:出荷ゾーン
     */
    @Column(name = "zone_type")
    Integer zoneType;
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
     * Returns the zoneName.
     *
     * @return the zoneName
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * Sets the zoneName.
     *
     * @param zoneName the zoneName
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * Returns the zoneType.
     *
     * @return the zoneType
     */
    public Integer getZoneType() {
        return zoneType;
    }

    /**
     * Sets the zoneType.
     *
     * @param zoneType the zoneType
     */
    public void setZoneType(Integer zoneType) {
        this.zoneType = zoneType;
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
