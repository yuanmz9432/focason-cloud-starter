// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.notification.repository;


import static java.util.stream.Collectors.toList;

import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.attribute.FsSort;
import com.focason.core.dao.Base004NotificationEntityDao;
import com.focason.core.dao.Base005NotificationReadEntityDao;
import com.focason.core.entity.Base004NotificationEntity;
import com.focason.core.entity.Base005NotificationReadEntity;
import com.focason.core.entity.UserNotificationEntity;
import com.focason.platform.notification.dao.NotificationDao;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * NotificationRepository
 * <p>
 * Repository layer responsible for data access operations related to notifications,
 * including saving new notifications, updating read status, and searching.
 * It acts as an abstraction layer over specific DAO interfaces.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationRepository
{
    /** DAO for the main notification entity (Base004). */
    private final Base004NotificationEntityDao base004NotificationEntityDao;

    /** DAO for the notification read status entity (Base005). */
    private final Base005NotificationReadEntityDao base005NotificationReadEntityDao;

    /** Custom DAO for complex notification queries (e.g., joins). */
    private final NotificationDao notificationDao;

    /**
     * Saves a new main notification entity.
     * Sets the notify timestamp before insertion.
     *
     * @param entity The Base004NotificationEntity to save.
     */
    public void save(@NotNull Base004NotificationEntity entity) {
        entity.setNotifyAt(LocalDateTime.now());
        base004NotificationEntityDao.insert(entity);
    }

    /**
     * Saves a list of notification read status entities (typically for bulk initialization).
     *
     * @param entities List of Base005NotificationReadEntity to save.
     */
    public void save(List<Base005NotificationReadEntity> entities) {
        if (entities == null || entities.isEmpty())
            return;
        base005NotificationReadEntityDao.insert(entities);
    }

    /**
     * Updates the read status of a single notification.
     *
     * @param entity The Base005NotificationReadEntity to update.
     */
    public void update(Base005NotificationReadEntity entity) {
        if (entity == null)
            return;
        base005NotificationReadEntityDao.update(entity);
    }

    /**
     * Updates a list of notification read status entities (typically for bulk update).
     *
     * @param entities List of Base005NotificationReadEntity to update.
     */
    public void update(List<Base005NotificationReadEntity> entities) {
        if (entities == null || entities.isEmpty())
            return;
        base005NotificationReadEntityDao.update(entities);
    }

    /**
     * Finds and paginates user notifications based on complex criteria.
     *
     * @param condition The search condition (e.g., UID, isRead status).
     * @param pagination The pagination settings.
     * @param sort The sorting criteria.
     * @return A result set containing paginated UserNotificationEntity and the total count.
     */
    public FsResultSet<UserNotificationEntity> findAll(Condition condition, @NotNull FsPagination pagination,
        Sort sort) {
        // Initialize selection options with pagination and enable count query
        var options = pagination.toSelectOptions().count();
        // Perform the select operation using the custom DAO
        var entities = notificationDao.selectAll(condition, options, sort, toList());
        return new FsResultSet<>(entities, options.getCount());
    }

    /**
     * Finds the read status entity (Base005) for a specific notification and user.
     *
     * @param uid The user ID.
     * @param notificationId The notification ID.
     * @return An Optional containing the Base005NotificationReadEntity if found.
     */
    public Optional<Base005NotificationReadEntity> findNotification(String uid, String notificationId) {
        // Renamed from findNotification to findUserNotification for clarity in the service layer
        return notificationDao.selectUserNotification(uid, notificationId);
    }

    /**
     * Finds all unread notifications for a specific user.
     *
     * @param uid The user ID.
     * @return A list of unread Base005NotificationReadEntity.
     */
    public List<Base005NotificationReadEntity> findUnreadNotifications(String uid) {
        return notificationDao.selectUnreadNotifications(uid);
    }

    /**
     * 搜索条件
     * Search Condition DTO for filtering notifications.
     */
    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @With
    public static class Condition
    {
        /**
         * 默认的搜索条件
         */
        public static final Condition DEFAULT = new Condition();

        /**
         * UID (用户唯一标识)
         */
        private String uid;
        /**
         * 既读フラグ (已读标志)
         */
        private boolean isRead;
        /**
         * 通知时间
         */
        private String notifyAt;
    }

    /**
     * 排序参数
     * Sort Parameter DTO for defining sorting column and direction.
     */
    @AllArgsConstructor
    @Value
    public static class Sort
    {

        /**
         * Default conditions
         */
        public static final Sort DEFAULT = new Sort(SortColumn.ID, FsSort.Direction.ASC);

        /**
         * Sort column
         */
        SortColumn column;

        /**
         * Sort direction
         */
        FsSort.Direction direction;

        /**
         * Converts this sort parameter into a SQL statement format.
         *
         * @return SQL statement fragment (e.g., "column_name ASC").
         */
        public String toSql() {
            return column.getColumnName() + " " + direction.name();
        }

        /**
         * Creates a new Sort parameter from a generic {@link FsSort}.
         *
         * @param sort The generic FsSort object.
         * @return The specific NotificationRepository.Sort parameter.
         */
        public static Sort fromFsSort(FsSort sort) {
            return new Sort(SortColumn.fromPropertyName(sort.property()), sort.direction());
        }
    }

    /**
     * 排序字段枚举
     * Enumeration of database columns that can be used for sorting.
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum SortColumn
    {

        ID("id", "id"),

        NOTIFICATION_ID("notification_id", "notification_id"),

        NOTIFY_AT("notify_at", "notify_at");

        /**
         * DTO/Resource 中的属性名 (Property name in DTO/Resource)
         */
        @Getter
        private final String propertyName;

        /**
         * 数据库中的列名 (Database column name)
         */
        @Getter
        private final String columnName;

        /**
         * Converts a property name string into the corresponding SortColumn enum value.
         *
         * @param propertyName The property name string.
         * @return The matching SortColumn.
         * @throws IllegalArgumentException if the property name is not supported.
         */
        public static SortColumn fromPropertyName(String propertyName) {
            return Arrays.stream(values())
                .filter(v -> v.propertyName.equals(propertyName))
                .findFirst()
                .orElseThrow(
                    () -> new IllegalArgumentException("propertyName = '" + propertyName + "' is not supported."));
        }
    }
}
