/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
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
@Table(name = "iv004_inventory")
public class Iv004InventoryEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 商品SKU */
    @Column(name = "product_sku")
    String productSku;
    /** ゾーンコード */
    @Column(name = "zone_code")
    String zoneCode;
    /** 棚ユニットコード */
    @Column(name = "shelf_unit_code")
    String shelfUnitCode;
    /** ロット番号 */
    @Column(name = "lot_number")
    String lotNumber;
    /** 実在庫数量:物理的にカウントされた在庫数 */
    @Column(name = "actual_stock")
    Integer actualStock;
    /** 利用可能在庫数量:顧客や顧客注文に対して利用可能な在庫数 */
    @Column(name = "available_stock")
    Integer availableStock;
    /** 予約在庫数量:顧客が商品を注文したが、まだ出荷されていない在庫数 */
    @Column(name = "reserved_stock")
    Integer reservedStock;
    /** バックオーダー在庫数量:現在は在庫切れであり、注文された商品の数量 */
    @Column(name = "back_order_stock")
    Integer backOrderStock;
    /** リードタイム在庫数量:供給者からの補充注文が到着するまでの間の在庫数 */
    @Column(name = "lead_time_stock")
    Integer leadTimeStock;
    /** 賞味期限 */
    @Column(name = "expiry_date")
    LocalDateTime expiryDate;
    /** 在庫ステータス:1:未検査, 2:検査合格, 3:検査不合格 */
    @Column(name = "inventory_status")
    Integer inventoryStatus;
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
     * Returns the lotNumber.
     *
     * @return the lotNumber
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Sets the lotNumber.
     *
     * @param lotNumber the lotNumber
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * Returns the actualStock.
     *
     * @return the actualStock
     */
    public Integer getActualStock() {
        return actualStock;
    }

    /**
     * Sets the actualStock.
     *
     * @param actualStock the actualStock
     */
    public void setActualStock(Integer actualStock) {
        this.actualStock = actualStock;
    }

    /**
     * Returns the availableStock.
     *
     * @return the availableStock
     */
    public Integer getAvailableStock() {
        return availableStock;
    }

    /**
     * Sets the availableStock.
     *
     * @param availableStock the availableStock
     */
    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    /**
     * Returns the reservedStock.
     *
     * @return the reservedStock
     */
    public Integer getReservedStock() {
        return reservedStock;
    }

    /**
     * Sets the reservedStock.
     *
     * @param reservedStock the reservedStock
     */
    public void setReservedStock(Integer reservedStock) {
        this.reservedStock = reservedStock;
    }

    /**
     * Returns the backOrderStock.
     *
     * @return the backOrderStock
     */
    public Integer getBackOrderStock() {
        return backOrderStock;
    }

    /**
     * Sets the backOrderStock.
     *
     * @param backOrderStock the backOrderStock
     */
    public void setBackOrderStock(Integer backOrderStock) {
        this.backOrderStock = backOrderStock;
    }

    /**
     * Returns the leadTimeStock.
     *
     * @return the leadTimeStock
     */
    public Integer getLeadTimeStock() {
        return leadTimeStock;
    }

    /**
     * Sets the leadTimeStock.
     *
     * @param leadTimeStock the leadTimeStock
     */
    public void setLeadTimeStock(Integer leadTimeStock) {
        this.leadTimeStock = leadTimeStock;
    }

    /**
     * Returns the expiryDate.
     *
     * @return the expiryDate
     */
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiryDate.
     *
     * @param expiryDate the expiryDate
     */
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the inventoryStatus.
     *
     * @return the inventoryStatus
     */
    public Integer getInventoryStatus() {
        return inventoryStatus;
    }

    /**
     * Sets the inventoryStatus.
     *
     * @param inventoryStatus the inventoryStatus
     */
    public void setInventoryStatus(Integer inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
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
