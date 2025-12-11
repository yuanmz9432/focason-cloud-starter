package com.focason.core.entity;

import java.time.LocalDateTime;
import lombok.Data;
import org.seasar.doma.*;

@Entity
@Data
public class UserNotificationEntity
{
    /** 通知ID */
    @Column(name = "notification_id")
    String notificationId;
    /** タイトル */
    @Column(name = "title")
    String title;
    /** 本文 */
    @Column(name = "content")
    String content;
    /** ステータス */
    @Column(name = "status")
    Integer status;
    /** 通知タイプ:1: システム通知, 2: メール通知, 3: アプリ内通知, 4: セキュリティ通知 */
    @Column(name = "type")
    Integer type;
    /** 通知日時 */
    @Column(name = "notify_at")
    LocalDateTime notifyAt;
    /** ユーザー識別子 */
    @Column(name = "uid")
    String uid;
    /** 既読フラグ */
    @Column(name = "is_read")
    Integer isRead;
    /** 既読日時 */
    @Column(name = "read_at")
    LocalDateTime readAt;
}
