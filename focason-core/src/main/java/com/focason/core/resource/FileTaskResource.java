package com.focason.core.resource;



import java.time.LocalDate;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ImportTaskResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class FileTaskResource extends FsResource
{
    /**
     * 行ID
     */
    private Integer id;
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
    private LocalDate receiveDate;
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
    /**
     * 異常情報
     */
    private String errorMessage;
}
