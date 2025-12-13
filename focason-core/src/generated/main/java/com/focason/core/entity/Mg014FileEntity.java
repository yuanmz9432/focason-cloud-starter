/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * ファイル
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg014_file")
public class Mg014FileEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** ファイルコード */
    @Column(name = "file_code")
    String fileCode;
    /** ストアコード */
    @Column(name = "store_code")
    String storeCode;
    /** ファイル名称 */
    @Column(name = "file_name")
    String fileName;
    /** タイプ:1:CSV, 2:Excel */
    @Column(name = "type")
    Integer type;
    /** 種類:1:商品, 2:受注依頼, 3:出荷依頼 */
    @Column(name = "category")
    Integer category;
    /** 場所 */
    @Column(name = "location")
    String location;
    /** ステータス:1:未処理, 2:正常処理済, 3:異常あり, 4:キャンセル */
    @Column(name = "status")
    Integer status;

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
     * Returns the storeCode.
     *
     * @return the storeCode
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * Sets the storeCode.
     *
     * @param storeCode the storeCode
     */
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    /**
     * Returns the fileName.
     *
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the fileName.
     *
     * @param fileName the fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the type.
     *
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Returns the category.
     *
     * @return the category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * Returns the location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
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
}
