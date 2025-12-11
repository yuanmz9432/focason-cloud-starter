package com.focason.core.request;



import java.time.LocalDate;
import lombok.Data;

/**
 * FileImportTaskRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class FileTaskCreateRequest
{
    /**
     * タスクコード
     */
    private String taskCode;
    /**
     * タスクタイプ
     */
    private Integer taskType;
    /**
     * 受信日
     */
    private LocalDate receiveData;
    /**
     * 処理順位
     */
    private Integer processOrder;
    /**
     * 営業単位
     */
    private String businessUnit;
    /**
     * モジュール
     */
    private Integer fileModule;
    /**
     * ファイル名称
     */
    private String fileName;
    /**
     * ファイルパス
     */
    private String filePath;
    /**
     * ファイルタイプ
     */
    private Integer fileType;
    /**
     * ステータス
     */
    private Integer status;
}
