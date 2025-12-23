/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "sp004_shipment_split")
public class Sp004ShipmentSplitEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 受注分割コード */
    @Column(name = "shipment_split_code")
    String shipmentSplitCode;
    /** 分割元受注コード */
    @Column(name = "original_shipment_code")
    String originalShipmentCode;
    /** 分割先受注コード */
    @Column(name = "split_shipment_code")
    String splitShipmentCode;
    /** 分割日時 */
    @Column(name = "split_datetime")
    LocalDateTime splitDatetime;

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
     * Returns the shipmentSplitCode.
     *
     * @return the shipmentSplitCode
     */
    public String getShipmentSplitCode() {
        return shipmentSplitCode;
    }

    /**
     * Sets the shipmentSplitCode.
     *
     * @param shipmentSplitCode the shipmentSplitCode
     */
    public void setShipmentSplitCode(String shipmentSplitCode) {
        this.shipmentSplitCode = shipmentSplitCode;
    }

    /**
     * Returns the originalShipmentCode.
     *
     * @return the originalShipmentCode
     */
    public String getOriginalShipmentCode() {
        return originalShipmentCode;
    }

    /**
     * Sets the originalShipmentCode.
     *
     * @param originalShipmentCode the originalShipmentCode
     */
    public void setOriginalShipmentCode(String originalShipmentCode) {
        this.originalShipmentCode = originalShipmentCode;
    }

    /**
     * Returns the splitShipmentCode.
     *
     * @return the splitShipmentCode
     */
    public String getSplitShipmentCode() {
        return splitShipmentCode;
    }

    /**
     * Sets the splitShipmentCode.
     *
     * @param splitShipmentCode the splitShipmentCode
     */
    public void setSplitShipmentCode(String splitShipmentCode) {
        this.splitShipmentCode = splitShipmentCode;
    }

    /**
     * Returns the splitDatetime.
     *
     * @return the splitDatetime
     */
    public LocalDateTime getSplitDatetime() {
        return splitDatetime;
    }

    /**
     * Sets the splitDatetime.
     *
     * @param splitDatetime the splitDatetime
     */
    public void setSplitDatetime(LocalDateTime splitDatetime) {
        this.splitDatetime = splitDatetime;
    }
}
