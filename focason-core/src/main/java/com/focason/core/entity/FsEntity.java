/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.entity;



import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity(listener = FsEntityListener.class)
@Data
@EqualsAndHashCode
public class FsEntity
{
    /** 作成者 */
    @Column(name = "created_by")
    String createdBy;

    /** 作成日時 */
    @Column(name = "created_at")
    LocalDateTime createdAt;

    /** 更新者 */
    @Column(name = "modified_by")
    String modifiedBy;

    /** 更新日時 */
    @Column(name = "modified_at")
    LocalDateTime modifiedAt;

    /** 削除フラグ（0: 未削除 1: 削除済） */
    @Column(name = "is_deleted")
    Integer isDeleted;
}
