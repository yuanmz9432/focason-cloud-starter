/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * VIEW
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "vw002_inventory_movement_history_search")
public class Vw002InventoryMovementHistorySearchEntity extends FsEntity
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
    /** 数量 */
    @Column(name = "quantity")
    Integer quantity;
    /** 棚コード */
    @Column(name = "shelf_code")
    String shelfCode;
    /** 棚名称 */
    @Column(name = "shelf_name")
    String shelfName;
    /** 移動元:棚ユニットコード */
    @Column(name = "source_shelf_unit_code")
    String sourceShelfUnitCode;
    /** 棚ユニット名称 */
    @Column(name = "source_shelf_unit_name")
    String sourceShelfUnitName;
    /** 移動先:棚ユニットコード */
    @Column(name = "destination_shelf_unit_code")
    String destinationShelfUnitCode;
    /** 棚ユニット名称 */
    @Column(name = "destination_shelf_unit_name")
    String destinationShelfUnitName;
    /** 作業員 */
    @Column(name = "operator")
    String operator;
    /** 操作日時 */
    @Column(name = "operation_datetime")
    LocalDateTime operationDatetime;
    /** 操作タイプ:1:受領上棚, 2:ピッキング移動, 3:補充移動, 4:調達移動, 5:返品処理, 6:在庫整理, 7:品質検査移動 */
    @Column(name = "operation_type")
    Integer operationType;
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
     * Returns the quantity.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
     * Returns the sourceShelfUnitCode.
     *
     * @return the sourceShelfUnitCode
     */
    public String getSourceShelfUnitCode() {
        return sourceShelfUnitCode;
    }

    /**
     * Sets the sourceShelfUnitCode.
     *
     * @param sourceShelfUnitCode the sourceShelfUnitCode
     */
    public void setSourceShelfUnitCode(String sourceShelfUnitCode) {
        this.sourceShelfUnitCode = sourceShelfUnitCode;
    }

    /**
     * Returns the sourceShelfUnitName.
     *
     * @return the sourceShelfUnitName
     */
    public String getSourceShelfUnitName() {
        return sourceShelfUnitName;
    }

    /**
     * Sets the sourceShelfUnitName.
     *
     * @param sourceShelfUnitName the sourceShelfUnitName
     */
    public void setSourceShelfUnitName(String sourceShelfUnitName) {
        this.sourceShelfUnitName = sourceShelfUnitName;
    }

    /**
     * Returns the destinationShelfUnitCode.
     *
     * @return the destinationShelfUnitCode
     */
    public String getDestinationShelfUnitCode() {
        return destinationShelfUnitCode;
    }

    /**
     * Sets the destinationShelfUnitCode.
     *
     * @param destinationShelfUnitCode the destinationShelfUnitCode
     */
    public void setDestinationShelfUnitCode(String destinationShelfUnitCode) {
        this.destinationShelfUnitCode = destinationShelfUnitCode;
    }

    /**
     * Returns the destinationShelfUnitName.
     *
     * @return the destinationShelfUnitName
     */
    public String getDestinationShelfUnitName() {
        return destinationShelfUnitName;
    }

    /**
     * Sets the destinationShelfUnitName.
     *
     * @param destinationShelfUnitName the destinationShelfUnitName
     */
    public void setDestinationShelfUnitName(String destinationShelfUnitName) {
        this.destinationShelfUnitName = destinationShelfUnitName;
    }

    /**
     * Returns the operator.
     *
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Sets the operator.
     *
     * @param operator the operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Returns the operationDatetime.
     *
     * @return the operationDatetime
     */
    public LocalDateTime getOperationDatetime() {
        return operationDatetime;
    }

    /**
     * Sets the operationDatetime.
     *
     * @param operationDatetime the operationDatetime
     */
    public void setOperationDatetime(LocalDateTime operationDatetime) {
        this.operationDatetime = operationDatetime;
    }

    /**
     * Returns the operationType.
     *
     * @return the operationType
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**
     * Sets the operationType.
     *
     * @param operationType the operationType
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
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
