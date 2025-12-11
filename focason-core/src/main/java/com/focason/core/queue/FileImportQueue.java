package com.focason.core.queue;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FileImportQueue
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Builder(toBuilder = true)
@Data
public class FileImportQueue
{
    /** タスクコード */
    private String taskCode;
    /** ファイル名称 */
    private String fileName;
    /** ファイルタイプ */
    private Integer fileType;
    /** ファイルパス */
    private String filePath;
}
