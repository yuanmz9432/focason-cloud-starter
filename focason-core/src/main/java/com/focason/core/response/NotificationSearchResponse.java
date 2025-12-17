package com.focason.core.response;

import java.time.LocalDateTime;

/**
 * NotificationSearchResponse
 *
 * @param notificationId 通知ID
 * @param title タイトル
 * @param content 本文
 * @param status ステータス
 * @param type 通知タイプ:1: システム通知, 2: 一部, 3: アプリ内通知, 4: セキュリティ通知
 * @param notifyAt 通知日時
 * @param uid ユーザー識別子
 * @param isRead 既読フラグ
 * @param readAt 既読日時
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record NotificationSearchResponse(String notificationId,String title,String content,Integer status,Integer type,LocalDateTime notifyAt,String uid,Integer isRead,LocalDateTime readAt){}
