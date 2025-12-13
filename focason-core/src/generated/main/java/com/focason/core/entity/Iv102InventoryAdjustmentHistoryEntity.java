/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 在庫調整履歴
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "iv102_inventory_adjustment_history")
public class Iv102InventoryAdjustmentHistoryEntity extends FsEntity
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
    /** 数量 */
    @Column(name = "quantity")
    Integer quantity;
    /** 調整前数量 */
    @Column(name = "quantity_before_change")
    Integer quantityBeforeChange;
    /** 調整後数量 */
    @Column(name = "quantity_after_change")
    Integer quantityAfterChange;
    /** 作業員 */
    @Column(name = "operator")
    String operator;
    /** 操作日時 */
    @Column(name = "operation_datetime")
    LocalDateTime operationDatetime;
    /** 操作タイプ:1:新規, 2:在庫増加, 3:在庫減少, 4:在庫移動 */
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
     * Returns the quantityBeforeChange.
     *
     * @return the quantityBeforeChange
     */
    public Integer getQuantityBeforeChange() {
        return quantityBeforeChange;
    }

    /**
     * Sets the quantityBeforeChange.
     *
     * @param quantityBeforeChange the quantityBeforeChange
     */
    public void setQuantityBeforeChange(Integer quantityBeforeChange) {
        this.quantityBeforeChange = quantityBeforeChange;
    }

    /**
     * Returns the quantityAfterChange.
     *
     * @return the quantityAfterChange
     */
    public Integer getQuantityAfterChange() {
        return quantityAfterChange;
    }

    /**
     * Sets the quantityAfterChange.
     *
     * @param quantityAfterChange the quantityAfterChange
     */
    public void setQuantityAfterChange(Integer quantityAfterChange) {
        this.quantityAfterChange = quantityAfterChange;
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
