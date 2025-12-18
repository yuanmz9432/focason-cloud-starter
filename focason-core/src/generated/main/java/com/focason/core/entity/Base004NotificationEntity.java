/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "base004_notification")
public class Base004NotificationEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
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

    /**
     * Returns the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the notificationId.
     *
     * @return the notificationId
     */
    public String getNotificationId() {
        return notificationId;
    }

    /**
     * Sets the notificationId.
     *
     * @param notificationId the notificationId
     */
    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Returns the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Returns the type.
     *
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Returns the notifyAt.
     *
     * @return the notifyAt
     */
    public LocalDateTime getNotifyAt() {
        return notifyAt;
    }

    /**
     * Sets the notifyAt.
     *
     * @param notifyAt the notifyAt
     */
    public void setNotifyAt(LocalDateTime notifyAt) {
        this.notifyAt = notifyAt;
    }
}
