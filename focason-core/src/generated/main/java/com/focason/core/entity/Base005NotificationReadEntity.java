/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
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
@Table(name = "base005_notification_read")
public class Base005NotificationReadEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 通知ID */
    @Column(name = "notification_id")
    String notificationId;
    /** ユーザー識別子 */
    @Column(name = "uid")
    String uid;
    /** 既読フラグ */
    @Column(name = "is_read")
    Integer isRead;
    /** 既読日時 */
    @Column(name = "read_at")
    LocalDateTime readAt;

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
     * Returns the uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the uid.
     *
     * @param uid the uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Returns the isRead.
     *
     * @return the isRead
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * Sets the isRead.
     *
     * @param isRead the isRead
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * Returns the readAt.
     *
     * @return the readAt
     */
    public LocalDateTime getReadAt() {
        return readAt;
    }

    /**
     * Sets the readAt.
     *
     * @param readAt the readAt
     */
    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }
}
