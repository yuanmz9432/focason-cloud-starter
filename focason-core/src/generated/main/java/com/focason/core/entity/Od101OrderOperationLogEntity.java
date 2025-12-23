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
@Table(name = "od101_order_operation_log")
public class Od101OrderOperationLogEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 受注操作履歴コード */
    @Column(name = "order_operation_log_code")
    String orderOperationLogCode;
    /** 受注コード */
    @Column(name = "order_code")
    String orderCode;
    /** 作業員 */
    @Column(name = "operator")
    String operator;
    /** 操作日時 */
    @Column(name = "operation_datetime")
    LocalDateTime operationDatetime;
    /** 操作タイプ:1:出荷依頼, 2:受注調整, 3:キャンセル */
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
     * Returns the orderOperationLogCode.
     *
     * @return the orderOperationLogCode
     */
    public String getOrderOperationLogCode() {
        return orderOperationLogCode;
    }

    /**
     * Sets the orderOperationLogCode.
     *
     * @param orderOperationLogCode the orderOperationLogCode
     */
    public void setOrderOperationLogCode(String orderOperationLogCode) {
        this.orderOperationLogCode = orderOperationLogCode;
    }

    /**
     * Returns the orderCode.
     *
     * @return the orderCode
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * Sets the orderCode.
     *
     * @param orderCode the orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
