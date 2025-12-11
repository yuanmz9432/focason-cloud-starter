package com.focason.core.request;



import lombok.Data;

/**
 * FileExportTaskRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class FileExportTaskRequest
{
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
     * 検索条件
     */
    private String filterConditions;
    /**
     * 出力項目
     */
    private String exportColumns;
}
