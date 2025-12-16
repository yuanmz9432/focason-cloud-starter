package com.focason.core.request;



import lombok.Data;

/**
 * NotificationRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class NotificationRequest
{
    /** 通知対象（UID配列） */
    private String[] targets;
    /** タイトル */
    private String title;
    /** 内容 */
    private String content;
    /** ステータス */
    private Integer status;
    /** タイプ */
    private Integer type;
}
