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
@Table(name = "od003_order_split")
public class Od003OrderSplitEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 受注分割コード */
    @Column(name = "order_split_code")
    String orderSplitCode;
    /** 分割元受注コード */
    @Column(name = "original_order_code")
    String originalOrderCode;
    /** 分割先受注コード */
    @Column(name = "split_order_code")
    String splitOrderCode;
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
     * Returns the orderSplitCode.
     *
     * @return the orderSplitCode
     */
    public String getOrderSplitCode() {
        return orderSplitCode;
    }

    /**
     * Sets the orderSplitCode.
     *
     * @param orderSplitCode the orderSplitCode
     */
    public void setOrderSplitCode(String orderSplitCode) {
        this.orderSplitCode = orderSplitCode;
    }

    /**
     * Returns the originalOrderCode.
     *
     * @return the originalOrderCode
     */
    public String getOriginalOrderCode() {
        return originalOrderCode;
    }

    /**
     * Sets the originalOrderCode.
     *
     * @param originalOrderCode the originalOrderCode
     */
    public void setOriginalOrderCode(String originalOrderCode) {
        this.originalOrderCode = originalOrderCode;
    }

    /**
     * Returns the splitOrderCode.
     *
     * @return the splitOrderCode
     */
    public String getSplitOrderCode() {
        return splitOrderCode;
    }

    /**
     * Sets the splitOrderCode.
     *
     * @param splitOrderCode the splitOrderCode
     */
    public void setSplitOrderCode(String splitOrderCode) {
        this.splitOrderCode = splitOrderCode;
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
