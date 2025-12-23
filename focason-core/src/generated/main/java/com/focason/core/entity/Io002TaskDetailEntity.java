/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "io002_task_detail")
public class Io002TaskDetailEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** タスクコード */
    @Column(name = "task_code")
    String taskCode;
    /** レコード順番 */
    @Column(name = "record_order")
    Integer recordOrder;
    /** ステータス */
    @Column(name = "status")
    Integer status;
    /** 項目1 */
    @Column(name = "item1")
    String item1;
    /** 項目2 */
    @Column(name = "item2")
    String item2;
    /** 項目3 */
    @Column(name = "item3")
    String item3;
    /** 項目4 */
    @Column(name = "item4")
    String item4;
    /** 項目5 */
    @Column(name = "item5")
    String item5;
    /** 項目6 */
    @Column(name = "item6")
    String item6;
    /** 項目7 */
    @Column(name = "item7")
    String item7;
    /** 項目8 */
    @Column(name = "item8")
    String item8;
    /** 項目9 */
    @Column(name = "item9")
    String item9;
    /** 項目10 */
    @Column(name = "item10")
    String item10;
    /** 項目11 */
    @Column(name = "item11")
    String item11;
    /** 項目12 */
    @Column(name = "item12")
    String item12;
    /** 項目13 */
    @Column(name = "item13")
    String item13;
    /** 項目14 */
    @Column(name = "item14")
    String item14;
    /** 項目15 */
    @Column(name = "item15")
    String item15;
    /** 項目16 */
    @Column(name = "item16")
    String item16;
    /** 項目17 */
    @Column(name = "item17")
    String item17;
    /** 項目18 */
    @Column(name = "item18")
    String item18;
    /** 項目19 */
    @Column(name = "item19")
    String item19;
    /** 項目20 */
    @Column(name = "item20")
    String item20;
    /** 異常情報 */
    @Column(name = "error_message")
    String errorMessage;

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
     * Returns the taskCode.
     *
     * @return the taskCode
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * Sets the taskCode.
     *
     * @param taskCode the taskCode
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**
     * Returns the recordOrder.
     *
     * @return the recordOrder
     */
    public Integer getRecordOrder() {
        return recordOrder;
    }

    /**
     * Sets the recordOrder.
     *
     * @param recordOrder the recordOrder
     */
    public void setRecordOrder(Integer recordOrder) {
        this.recordOrder = recordOrder;
    }

    /**
     * Returns the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Returns the item1.
     *
     * @return the item1
     */
    public String getItem1() {
        return item1;
    }

    /**
     * Sets the item1.
     *
     * @param item1 the item1
     */
    public void setItem1(String item1) {
        this.item1 = item1;
    }

    /**
     * Returns the item2.
     *
     * @return the item2
     */
    public String getItem2() {
        return item2;
    }

    /**
     * Sets the item2.
     *
     * @param item2 the item2
     */
    public void setItem2(String item2) {
        this.item2 = item2;
    }

    /**
     * Returns the item3.
     *
     * @return the item3
     */
    public String getItem3() {
        return item3;
    }

    /**
     * Sets the item3.
     *
     * @param item3 the item3
     */
    public void setItem3(String item3) {
        this.item3 = item3;
    }

    /**
     * Returns the item4.
     *
     * @return the item4
     */
    public String getItem4() {
        return item4;
    }

    /**
     * Sets the item4.
     *
     * @param item4 the item4
     */
    public void setItem4(String item4) {
        this.item4 = item4;
    }

    /**
     * Returns the item5.
     *
     * @return the item5
     */
    public String getItem5() {
        return item5;
    }

    /**
     * Sets the item5.
     *
     * @param item5 the item5
     */
    public void setItem5(String item5) {
        this.item5 = item5;
    }

    /**
     * Returns the item6.
     *
     * @return the item6
     */
    public String getItem6() {
        return item6;
    }

    /**
     * Sets the item6.
     *
     * @param item6 the item6
     */
    public void setItem6(String item6) {
        this.item6 = item6;
    }

    /**
     * Returns the item7.
     *
     * @return the item7
     */
    public String getItem7() {
        return item7;
    }

    /**
     * Sets the item7.
     *
     * @param item7 the item7
     */
    public void setItem7(String item7) {
        this.item7 = item7;
    }

    /**
     * Returns the item8.
     *
     * @return the item8
     */
    public String getItem8() {
        return item8;
    }

    /**
     * Sets the item8.
     *
     * @param item8 the item8
     */
    public void setItem8(String item8) {
        this.item8 = item8;
    }

    /**
     * Returns the item9.
     *
     * @return the item9
     */
    public String getItem9() {
        return item9;
    }

    /**
     * Sets the item9.
     *
     * @param item9 the item9
     */
    public void setItem9(String item9) {
        this.item9 = item9;
    }

    /**
     * Returns the item10.
     *
     * @return the item10
     */
    public String getItem10() {
        return item10;
    }

    /**
     * Sets the item10.
     *
     * @param item10 the item10
     */
    public void setItem10(String item10) {
        this.item10 = item10;
    }

    /**
     * Returns the item11.
     *
     * @return the item11
     */
    public String getItem11() {
        return item11;
    }

    /**
     * Sets the item11.
     *
     * @param item11 the item11
     */
    public void setItem11(String item11) {
        this.item11 = item11;
    }

    /**
     * Returns the item12.
     *
     * @return the item12
     */
    public String getItem12() {
        return item12;
    }

    /**
     * Sets the item12.
     *
     * @param item12 the item12
     */
    public void setItem12(String item12) {
        this.item12 = item12;
    }

    /**
     * Returns the item13.
     *
     * @return the item13
     */
    public String getItem13() {
        return item13;
    }

    /**
     * Sets the item13.
     *
     * @param item13 the item13
     */
    public void setItem13(String item13) {
        this.item13 = item13;
    }

    /**
     * Returns the item14.
     *
     * @return the item14
     */
    public String getItem14() {
        return item14;
    }

    /**
     * Sets the item14.
     *
     * @param item14 the item14
     */
    public void setItem14(String item14) {
        this.item14 = item14;
    }

    /**
     * Returns the item15.
     *
     * @return the item15
     */
    public String getItem15() {
        return item15;
    }

    /**
     * Sets the item15.
     *
     * @param item15 the item15
     */
    public void setItem15(String item15) {
        this.item15 = item15;
    }

    /**
     * Returns the item16.
     *
     * @return the item16
     */
    public String getItem16() {
        return item16;
    }

    /**
     * Sets the item16.
     *
     * @param item16 the item16
     */
    public void setItem16(String item16) {
        this.item16 = item16;
    }

    /**
     * Returns the item17.
     *
     * @return the item17
     */
    public String getItem17() {
        return item17;
    }

    /**
     * Sets the item17.
     *
     * @param item17 the item17
     */
    public void setItem17(String item17) {
        this.item17 = item17;
    }

    /**
     * Returns the item18.
     *
     * @return the item18
     */
    public String getItem18() {
        return item18;
    }

    /**
     * Sets the item18.
     *
     * @param item18 the item18
     */
    public void setItem18(String item18) {
        this.item18 = item18;
    }

    /**
     * Returns the item19.
     *
     * @return the item19
     */
    public String getItem19() {
        return item19;
    }

    /**
     * Sets the item19.
     *
     * @param item19 the item19
     */
    public void setItem19(String item19) {
        this.item19 = item19;
    }

    /**
     * Returns the item20.
     *
     * @return the item20
     */
    public String getItem20() {
        return item20;
    }

    /**
     * Sets the item20.
     *
     * @param item20 the item20
     */
    public void setItem20(String item20) {
        this.item20 = item20;
    }

    /**
     * Returns the errorMessage.
     *
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the errorMessage.
     *
     * @param errorMessage the errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
