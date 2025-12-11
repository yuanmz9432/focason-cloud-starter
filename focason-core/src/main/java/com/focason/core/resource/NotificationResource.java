package com.focason.core.resource;



import java.time.LocalDateTime;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NotificationResource
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
public class NotificationResource
{
    /** 行ID */
    private Integer id;
    /** 通知ID */
    private String notificationId;
    /** ステータス */
    private String[] targets;
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
}
