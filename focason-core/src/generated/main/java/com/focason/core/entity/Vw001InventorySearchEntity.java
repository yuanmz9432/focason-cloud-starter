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
@Table(name = "vw001_inventory_search")
public class Vw001InventorySearchEntity extends FsEntity
{
    /** 行ID */
    @Column(name = "id")
    Integer id;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 倉庫名称 */
    @Column(name = "warehouse_name")
    String warehouseName;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 荷主名称 */
    @Column(name = "client_name")
    String clientName;
    /** 商品SKU */
    @Column(name = "product_sku")
    String productSku;
    /** 商品名称 */
    @Column(name = "product_name")
    String productName;
    /** 商品タイプ:1:通常商品、2:セット商品、3:同梱品 */
    @Column(name = "product_type")
    Integer productType;
    /** 棚コード */
    @Column(name = "shelf_code")
    String shelfCode;
    /** 棚名称 */
    @Column(name = "shelf_name")
    String shelfName;
    /** 棚ユニットコード */
    @Column(name = "shelf_unit_code")
    String shelfUnitCode;
    /** 棚ユニット名称 */
    @Column(name = "shelf_unit_name")
    String shelfUnitName;
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
    /** 在庫ステータス:1:未検査, 2:検査合格, 3:検査不合格 */
    @Column(name = "inventory_status")
    Integer inventoryStatus;
    /** 賞味期限 */
    @Column(name = "expiry_date")
    LocalDateTime expiryDate;
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
     * Returns the clientName.
     *
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the clientName.
     *
     * @param clientName the clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
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
     * Returns the productName.
     *
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the productName.
     *
     * @param productName the productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the productType.
     *
     * @return the productType
     */
    public Integer getProductType() {
        return productType;
    }

    /**
     * Sets the productType.
     *
     * @param productType the productType
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
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
     * Returns the shelfName.
     *
     * @return the shelfName
     */
    public String getShelfName() {
        return shelfName;
    }

    /**
     * Sets the shelfName.
     *
     * @param shelfName the shelfName
     */
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
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
