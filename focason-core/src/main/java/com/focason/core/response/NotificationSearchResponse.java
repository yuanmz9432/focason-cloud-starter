package com.focason.core.response;



import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NotificationSearchResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class NotificationSearchResponse
{
    /** 通知ID */
    private String notificationId;
    /** タイトル */
    private String title;
    /** 本文 */
    private String content;
    /** ステータス */
    private Integer status;
    /** 通知タイプ:1: システム通知, 2: 一部, 3: アプリ内通知, 4: セキュリティ通知 */
    private Integer type;
    /** 通知日時 */
    private LocalDateTime notifyAt;
    /** ユーザー識別子 */
    private String uid;
    /** 通知ID */
    private Integer isRead;
    /** ユーザー識別子 */
    private LocalDateTime readAt;
}
