package com.focason.core.resource;



import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FsResource
{
    /** 作成者 */
    String createdBy;

    /** 作成日時 */
    LocalDateTime createdAt;

    /** 更新者 */
    String modifiedBy;

    /** 更新日時 */
    LocalDateTime modifiedAt;

    /** 削除フラグ（0: 未削除 1: 削除済） */
    Integer isDeleted;
}
