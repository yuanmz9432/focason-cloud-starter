package com.focason.core.request;

/**
 * NotificationRequest
 *
 * @param targets 通知対象（UID配列）
 * @param title タイトル
 * @param content 内容
 * @param status ステータス
 * @param type タイプ
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record NotificationRequest(String[]targets,String title,String content,Integer status,Integer type){}
