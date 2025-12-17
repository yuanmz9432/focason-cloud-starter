package com.focason.core.request;

import java.time.LocalDate;

/**
 * FileImportTaskRequest
 *
 * @param taskCode タスクコード
 * @param taskType タスクタイプ
 * @param receiveData 受信日
 * @param processOrder 処理順位
 * @param businessUnit 営業単位
 * @param fileModule モジュール
 * @param fileName ファイル名称
 * @param filePath ファイルパス
 * @param fileType ファイルタイプ
 * @param status ステータス
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record FileTaskCreateRequest(String taskCode,Integer taskType,LocalDate receiveData,Integer processOrder,String businessUnit,Integer fileModule,String fileName,String filePath,Integer fileType,Integer status){}
