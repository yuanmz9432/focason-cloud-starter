/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 入庫履歴
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "iv104_inbound_log")
public class Iv104InboundLogEntity extends FsEntity
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
    /** 棚ユニットコード */
    @Column(name = "shelf_unit_code")
    String shelfUnitCode;
    /** 作業員 */
    @Column(name = "operator")
    String operator;
    /** 操作日時 */
    @Column(name = "operation_datetime")
    LocalDateTime operationDatetime;
    /** 操作タイプ:1:仕入れ受領, 2:入庫済み, 3:キャンセル */
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
