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
@Table(name = "od004_order_consolidation")
public class Od004OrderConsolidationEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 受注統合コード */
    @Column(name = "order_consolidation_code")
    String orderConsolidationCode;
    /** 統合先受注コード */
    @Column(name = "consolidated_order_code")
    String consolidatedOrderCode;
    /** 統合元受注コード */
    @Column(name = "original_order_code")
    String originalOrderCode;
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
     * Returns the orderConsolidationCode.
     *
     * @return the orderConsolidationCode
     */
    public String getOrderConsolidationCode() {
        return orderConsolidationCode;
    }

    /**
     * Sets the orderConsolidationCode.
     *
     * @param orderConsolidationCode the orderConsolidationCode
     */
    public void setOrderConsolidationCode(String orderConsolidationCode) {
        this.orderConsolidationCode = orderConsolidationCode;
    }

    /**
     * Returns the consolidatedOrderCode.
     *
     * @return the consolidatedOrderCode
     */
    public String getConsolidatedOrderCode() {
        return consolidatedOrderCode;
    }

    /**
     * Sets the consolidatedOrderCode.
     *
     * @param consolidatedOrderCode the consolidatedOrderCode
     */
    public void setConsolidatedOrderCode(String consolidatedOrderCode) {
        this.consolidatedOrderCode = consolidatedOrderCode;
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
