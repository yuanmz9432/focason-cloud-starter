package com.focason.core.response;

import java.time.LocalDate;

/**
 * FileTaskSearchResponse
 *
 * @param id 行ID
 * @param taskCode タスクコード
 * @param taskType タスクタイプ
 * @param receiveDate 受信日
 * @param processOrder 処理順位
 * @param businessUnit 営業単位
 * @param fileModule モジュール
 * @param fileName ファイル名称
 * @param filePath ファイルパス
 * @param fileType ファイルタイプ
 * @param status ステータス
 * @param errorMessage 異常情報
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record FileTaskSearchResponse(Integer id,String taskCode,Integer taskType,LocalDate receiveDate,Integer processOrder,String businessUnit,Integer fileModule,String fileName,String filePath,Integer fileType,Integer status,String errorMessage){}
