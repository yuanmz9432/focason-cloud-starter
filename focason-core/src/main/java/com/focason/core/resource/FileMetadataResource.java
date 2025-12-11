package com.focason.core.resource;


import com.focason.core.domain.FileMimeType;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FileMetadataResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Builder(toBuilder = true)
@Data
public class FileMetadataResource
{
    /** ユーザー識別子 */
    private String uid;
    /** ファイルID */
    private String fileId;
    /** ファイル名 */
    private String originalFileName;
    /** オブジェクトキー */
    private String objectKey;
    /** タイプ */
    private FileMimeType mimeType;
    /** サイズ */
    private String size;
    /** ステータス */
    private String status;
    /** ダウンロード数 */
    private String downloadCount;
    /** 最終ダウンロード日時 */
    private String lastDownloadDatetime;
}
