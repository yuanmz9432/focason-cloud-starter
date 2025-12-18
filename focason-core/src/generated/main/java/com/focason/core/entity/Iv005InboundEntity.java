/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDate;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "iv005_inbound")
public class Iv005InboundEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 入庫コード */
    @Column(name = "inbound_code")
    String inboundCode;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** サプライヤーコード */
    @Column(name = "supplier_code")
    String supplierCode;
    /** 入庫依頼日付 */
    @Column(name = "request_inbound_date")
    LocalDate requestInboundDate;
    /** 予想入庫日付 */
    @Column(name = "expected_inbound_date")
    LocalDate expectedInboundDate;
    /** 実際入庫日付 */
    @Column(name = "actual_inbound_date")
    LocalDate actualInboundDate;
    /** 入庫ステータス:1:未処理, 2:進行中, 3:入庫済み, 4:キャンセル, 5:異常 */
    @Column(name = "inbound_status")
    Integer inboundStatus;
    /** 入庫タイプ:1:通常入庫, 2:返品入庫, 3:調達入庫, 4:棚卸差異調整入庫 */
    @Column(name = "inbound_type")
    Integer inboundType;
    /** 配送伝票番号 */
    @Column(name = "shipment_manifest_number")
    String shipmentManifestNumber;
    /** 荷主側担当者 */
    @Column(name = "client_manager")
    String clientManager;
    /** 倉庫側担当者 */
    @Column(name = "warehouse_manager")
    String warehouseManager;
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
     * Returns the inboundCode.
     *
     * @return the inboundCode
     */
    public String getInboundCode() {
        return inboundCode;
    }

    /**
     * Sets the inboundCode.
     *
     * @param inboundCode the inboundCode
     */
    public void setInboundCode(String inboundCode) {
        this.inboundCode = inboundCode;
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
     * Returns the requestInboundDate.
     *
     * @return the requestInboundDate
     */
    public LocalDate getRequestInboundDate() {
        return requestInboundDate;
    }

    /**
     * Sets the requestInboundDate.
     *
     * @param requestInboundDate the requestInboundDate
     */
    public void setRequestInboundDate(LocalDate requestInboundDate) {
        this.requestInboundDate = requestInboundDate;
    }

    /**
     * Returns the expectedInboundDate.
     *
     * @return the expectedInboundDate
     */
    public LocalDate getExpectedInboundDate() {
        return expectedInboundDate;
    }

    /**
     * Sets the expectedInboundDate.
     *
     * @param expectedInboundDate the expectedInboundDate
     */
    public void setExpectedInboundDate(LocalDate expectedInboundDate) {
        this.expectedInboundDate = expectedInboundDate;
    }

    /**
     * Returns the actualInboundDate.
     *
     * @return the actualInboundDate
     */
    public LocalDate getActualInboundDate() {
        return actualInboundDate;
    }

    /**
     * Sets the actualInboundDate.
     *
     * @param actualInboundDate the actualInboundDate
     */
    public void setActualInboundDate(LocalDate actualInboundDate) {
        this.actualInboundDate = actualInboundDate;
    }

    /**
     * Returns the inboundStatus.
     *
     * @return the inboundStatus
     */
    public Integer getInboundStatus() {
        return inboundStatus;
    }

    /**
     * Sets the inboundStatus.
     *
     * @param inboundStatus the inboundStatus
     */
    public void setInboundStatus(Integer inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * Returns the inboundType.
     *
     * @return the inboundType
     */
    public Integer getInboundType() {
        return inboundType;
    }

    /**
     * Sets the inboundType.
     *
     * @param inboundType the inboundType
     */
    public void setInboundType(Integer inboundType) {
        this.inboundType = inboundType;
    }

    /**
     * Returns the shipmentManifestNumber.
     *
     * @return the shipmentManifestNumber
     */
    public String getShipmentManifestNumber() {
        return shipmentManifestNumber;
    }

    /**
     * Sets the shipmentManifestNumber.
     *
     * @param shipmentManifestNumber the shipmentManifestNumber
     */
    public void setShipmentManifestNumber(String shipmentManifestNumber) {
        this.shipmentManifestNumber = shipmentManifestNumber;
    }

    /**
     * Returns the clientManager.
     *
     * @return the clientManager
     */
    public String getClientManager() {
        return clientManager;
    }

    /**
     * Sets the clientManager.
     *
     * @param clientManager the clientManager
     */
    public void setClientManager(String clientManager) {
        this.clientManager = clientManager;
    }

    /**
     * Returns the warehouseManager.
     *
     * @return the warehouseManager
     */
    public String getWarehouseManager() {
        return warehouseManager;
    }

    /**
     * Sets the warehouseManager.
     *
     * @param warehouseManager the warehouseManager
     */
    public void setWarehouseManager(String warehouseManager) {
        this.warehouseManager = warehouseManager;
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
