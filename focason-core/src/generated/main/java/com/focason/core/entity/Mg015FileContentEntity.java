/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * ファイル内容
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg015_file_content")
public class Mg015FileContentEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** ファイルコード */
    @Column(name = "file_code")
    String fileCode;
    /** シート番号 */
    @Column(name = "sheet_number")
    Integer sheetNumber;
    /** 項目1 */
    @Column(name = "value_1")
    String value1;
    /** 項目2 */
    @Column(name = "value_2")
    String value2;
    /** 項目3 */
    @Column(name = "value_3")
    String value3;
    /** 項目4 */
    @Column(name = "value_4")
    String value4;
    /** 項目5 */
    @Column(name = "value_5")
    String value5;
    /** 項目6 */
    @Column(name = "value_6")
    String value6;
    /** 項目7 */
    @Column(name = "value_7")
    String value7;
    /** 項目8 */
    @Column(name = "value_8")
    String value8;
    /** 項目9 */
    @Column(name = "value_9")
    String value9;
    /** 項目10 */
    @Column(name = "value_10")
    String value10;
    /** 項目11 */
    @Column(name = "value_11")
    String value11;
    /** 項目12 */
    @Column(name = "value_12")
    String value12;
    /** 項目13 */
    @Column(name = "value_13")
    String value13;
    /** 項目14 */
    @Column(name = "value_14")
    String value14;
    /** 項目15 */
    @Column(name = "value_15")
    String value15;
    /** 項目16 */
    @Column(name = "value_16")
    String value16;
    /** 項目17 */
    @Column(name = "value_17")
    String value17;
    /** 項目18 */
    @Column(name = "value_18")
    String value18;
    /** 項目19 */
    @Column(name = "value_19")
    String value19;
    /** 項目20 */
    @Column(name = "value_20")
    String value20;

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
     * Returns the fileCode.
     *
     * @return the fileCode
     */
    public String getFileCode() {
        return fileCode;
    }

    /**
     * Sets the fileCode.
     *
     * @param fileCode the fileCode
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    /**
     * Returns the sheetNumber.
     *
     * @return the sheetNumber
     */
    public Integer getSheetNumber() {
        return sheetNumber;
    }

    /**
     * Sets the sheetNumber.
     *
     * @param sheetNumber the sheetNumber
     */
    public void setSheetNumber(Integer sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    /**
     * Returns the value1.
     *
     * @return the value1
     */
    public String getValue1() {
        return value1;
    }

    /**
     * Sets the value1.
     *
     * @param value1 the value1
     */
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    /**
     * Returns the value2.
     *
     * @return the value2
     */
    public String getValue2() {
        return value2;
    }

    /**
     * Sets the value2.
     *
     * @param value2 the value2
     */
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    /**
     * Returns the value3.
     *
     * @return the value3
     */
    public String getValue3() {
        return value3;
    }

    /**
     * Sets the value3.
     *
     * @param value3 the value3
     */
    public void setValue3(String value3) {
        this.value3 = value3;
    }

    /**
     * Returns the value4.
     *
     * @return the value4
     */
    public String getValue4() {
        return value4;
    }

    /**
     * Sets the value4.
     *
     * @param value4 the value4
     */
    public void setValue4(String value4) {
        this.value4 = value4;
    }

    /**
     * Returns the value5.
     *
     * @return the value5
     */
    public String getValue5() {
        return value5;
    }

    /**
     * Sets the value5.
     *
     * @param value5 the value5
     */
    public void setValue5(String value5) {
        this.value5 = value5;
    }

    /**
     * Returns the value6.
     *
     * @return the value6
     */
    public String getValue6() {
        return value6;
    }

    /**
     * Sets the value6.
     *
     * @param value6 the value6
     */
    public void setValue6(String value6) {
        this.value6 = value6;
    }

    /**
     * Returns the value7.
     *
     * @return the value7
     */
    public String getValue7() {
        return value7;
    }

    /**
     * Sets the value7.
     *
     * @param value7 the value7
     */
    public void setValue7(String value7) {
        this.value7 = value7;
    }

    /**
     * Returns the value8.
     *
     * @return the value8
     */
    public String getValue8() {
        return value8;
    }

    /**
     * Sets the value8.
     *
     * @param value8 the value8
     */
    public void setValue8(String value8) {
        this.value8 = value8;
    }

    /**
     * Returns the value9.
     *
     * @return the value9
     */
    public String getValue9() {
        return value9;
    }

    /**
     * Sets the value9.
     *
     * @param value9 the value9
     */
    public void setValue9(String value9) {
        this.value9 = value9;
    }

    /**
     * Returns the value10.
     *
     * @return the value10
     */
    public String getValue10() {
        return value10;
    }

    /**
     * Sets the value10.
     *
     * @param value10 the value10
     */
    public void setValue10(String value10) {
        this.value10 = value10;
    }

    /**
     * Returns the value11.
     *
     * @return the value11
     */
    public String getValue11() {
        return value11;
    }

    /**
     * Sets the value11.
     *
     * @param value11 the value11
     */
    public void setValue11(String value11) {
        this.value11 = value11;
    }

    /**
     * Returns the value12.
     *
     * @return the value12
     */
    public String getValue12() {
        return value12;
    }

    /**
     * Sets the value12.
     *
     * @param value12 the value12
     */
    public void setValue12(String value12) {
        this.value12 = value12;
    }

    /**
     * Returns the value13.
     *
     * @return the value13
     */
    public String getValue13() {
        return value13;
    }

    /**
     * Sets the value13.
     *
     * @param value13 the value13
     */
    public void setValue13(String value13) {
        this.value13 = value13;
    }

    /**
     * Returns the value14.
     *
     * @return the value14
     */
    public String getValue14() {
        return value14;
    }

    /**
     * Sets the value14.
     *
     * @param value14 the value14
     */
    public void setValue14(String value14) {
        this.value14 = value14;
    }

    /**
     * Returns the value15.
     *
     * @return the value15
     */
    public String getValue15() {
        return value15;
    }

    /**
     * Sets the value15.
     *
     * @param value15 the value15
     */
    public void setValue15(String value15) {
        this.value15 = value15;
    }

    /**
     * Returns the value16.
     *
     * @return the value16
     */
    public String getValue16() {
        return value16;
    }

    /**
     * Sets the value16.
     *
     * @param value16 the value16
     */
    public void setValue16(String value16) {
        this.value16 = value16;
    }

    /**
     * Returns the value17.
     *
     * @return the value17
     */
    public String getValue17() {
        return value17;
    }

    /**
     * Sets the value17.
     *
     * @param value17 the value17
     */
    public void setValue17(String value17) {
        this.value17 = value17;
    }

    /**
     * Returns the value18.
     *
     * @return the value18
     */
    public String getValue18() {
        return value18;
    }

    /**
     * Sets the value18.
     *
     * @param value18 the value18
     */
    public void setValue18(String value18) {
        this.value18 = value18;
    }

    /**
     * Returns the value19.
     *
     * @return the value19
     */
    public String getValue19() {
        return value19;
    }

    /**
     * Sets the value19.
     *
     * @param value19 the value19
     */
    public void setValue19(String value19) {
        this.value19 = value19;
    }

    /**
     * Returns the value20.
     *
     * @return the value20
     */
    public String getValue20() {
        return value20;
    }

    /**
     * Sets the value20.
     *
     * @param value20 the value20
     */
    public void setValue20(String value20) {
        this.value20 = value20;
    }
}
