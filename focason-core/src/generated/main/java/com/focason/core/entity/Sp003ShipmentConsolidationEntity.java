/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 出荷統合
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "sp003_shipment_consolidation")
public class Sp003ShipmentConsolidationEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 出荷統合コード */
    @Column(name = "shipment_consolidation_code")
    String shipmentConsolidationCode;
    /** 統合先出荷コード */
    @Column(name = "consolidated_shipment_code")
    String consolidatedShipmentCode;
    /** 統合元出荷コード */
    @Column(name = "original_shipment_code")
    String originalShipmentCode;
    /** 統合日時 */
    @Column(name = "consolidation_datetime")
    LocalDateTime consolidationDatetime;

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
     * Returns the shipmentConsolidationCode.
     *
     * @return the shipmentConsolidationCode
     */
    public String getShipmentConsolidationCode() {
        return shipmentConsolidationCode;
    }

    /**
     * Sets the shipmentConsolidationCode.
     *
     * @param shipmentConsolidationCode the shipmentConsolidationCode
     */
    public void setShipmentConsolidationCode(String shipmentConsolidationCode) {
        this.shipmentConsolidationCode = shipmentConsolidationCode;
    }

    /**
     * Returns the consolidatedShipmentCode.
     *
     * @return the consolidatedShipmentCode
     */
    public String getConsolidatedShipmentCode() {
        return consolidatedShipmentCode;
    }

    /**
     * Sets the consolidatedShipmentCode.
     *
     * @param consolidatedShipmentCode the consolidatedShipmentCode
     */
    public void setConsolidatedShipmentCode(String consolidatedShipmentCode) {
        this.consolidatedShipmentCode = consolidatedShipmentCode;
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
     * Returns the consolidationDatetime.
     *
     * @return the consolidationDatetime
     */
    public LocalDateTime getConsolidationDatetime() {
        return consolidationDatetime;
    }

    /**
     * Sets the consolidationDatetime.
     *
     * @param consolidationDatetime the consolidationDatetime
     */
    public void setConsolidationDatetime(LocalDateTime consolidationDatetime) {
        this.consolidationDatetime = consolidationDatetime;
    }
}
