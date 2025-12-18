/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDate;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "io001_file_task")
public class Io001FileTaskEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** タスクコード */
    @Column(name = "task_code")
    String taskCode;
    /** タスクタイプ */
    @Column(name = "task_type")
    Integer taskType;
    /** 受信日 */
    @Column(name = "receive_date")
    LocalDate receiveDate;
    /** 処理順位 */
    @Column(name = "process_order")
    Integer processOrder;
    /** 営業単位 */
    @Column(name = "business_unit")
    String businessUnit;
    /** ファイルモジュール */
    @Column(name = "file_module")
    Integer fileModule;
    /** ファイル名称 */
    @Column(name = "file_name")
    String fileName;
    /** ファイルパス */
    @Column(name = "file_path")
    String filePath;
    /** ファイルタイプ */
    @Column(name = "file_type")
    Integer fileType;
    /** 検索条件 */
    @Column(name = "filter_conditions")
    String filterConditions;
    /** 出力項目 */
    @Column(name = "export_columns")
    String exportColumns;
    /** ステータス */
    @Column(name = "status")
    Integer status;
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
     * Returns the taskType.
     *
     * @return the taskType
     */
    public Integer getTaskType() {
        return taskType;
    }

    /**
     * Sets the taskType.
     *
     * @param taskType the taskType
     */
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns the receiveDate.
     *
     * @return the receiveDate
     */
    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    /**
     * Sets the receiveDate.
     *
     * @param receiveDate the receiveDate
     */
    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * Returns the processOrder.
     *
     * @return the processOrder
     */
    public Integer getProcessOrder() {
        return processOrder;
    }

    /**
     * Sets the processOrder.
     *
     * @param processOrder the processOrder
     */
    public void setProcessOrder(Integer processOrder) {
        this.processOrder = processOrder;
    }

    /**
     * Returns the businessUnit.
     *
     * @return the businessUnit
     */
    public String getBusinessUnit() {
        return businessUnit;
    }

    /**
     * Sets the businessUnit.
     *
     * @param businessUnit the businessUnit
     */
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    /**
     * Returns the fileModule.
     *
     * @return the fileModule
     */
    public Integer getFileModule() {
        return fileModule;
    }

    /**
     * Sets the fileModule.
     *
     * @param fileModule the fileModule
     */
    public void setFileModule(Integer fileModule) {
        this.fileModule = fileModule;
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
     * Returns the filePath.
     *
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the filePath.
     *
     * @param filePath the filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the fileType.
     *
     * @return the fileType
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * Sets the fileType.
     *
     * @param fileType the fileType
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * Returns the filterConditions.
     *
     * @return the filterConditions
     */
    public String getFilterConditions() {
        return filterConditions;
    }

    /**
     * Sets the filterConditions.
     *
     * @param filterConditions the filterConditions
     */
    public void setFilterConditions(String filterConditions) {
        this.filterConditions = filterConditions;
    }

    /**
     * Returns the exportColumns.
     *
     * @return the exportColumns
     */
    public String getExportColumns() {
        return exportColumns;
    }

    /**
     * Sets the exportColumns.
     *
     * @param exportColumns the exportColumns
     */
    public void setExportColumns(String exportColumns) {
        this.exportColumns = exportColumns;
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
