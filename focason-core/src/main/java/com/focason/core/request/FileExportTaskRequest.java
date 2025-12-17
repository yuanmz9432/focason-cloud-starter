package com.focason.core.request;

/**
 * FileExportTaskRequest
 *
 * @param businessUnit 営業単位
 * @param fileModule モジュール
 * @param fileName ファイル名称
 * @param filePath ファイルパス
 * @param fileType ファイルタイプ
 * @param filterConditions 検索条件
 * @param exportColumns 出力項目
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record FileExportTaskRequest(String businessUnit,Integer fileModule,String fileName,String filePath,Integer fileType,String filterConditions,String exportColumns){}
