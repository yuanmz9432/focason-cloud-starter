// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.base.notification.dao;

import com.focason.base.notification.repository.NotificationRepository;
import com.focason.core.entity.Base005NotificationReadEntity;
import com.focason.core.entity.UserNotificationEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * NotificationDao
 * <p>
 * Doma Data Access Object (DAO) interface for querying notification-related data.
 * This DAO provides methods for complex queries involving joins and conditions
 * that are not covered by the generic DAOs (Base004 and Base005).
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Dao
@ConfigAutowireable // Doma-boot annotation to make this DAO auto-injectable
public interface NotificationDao
{
    /**
     * Finds and paginates all notifications visible to a user, typically joining
     * Base004 (notification content) and Base005 (read status).
     *
     * <p>
     * Uses {@code SelectType.COLLECT} to allow complex result handling (like streaming or list collection)
     * and supports Doma's {@code SelectOptions} for pagination and counting.
     * </p>
     *
     * @param <R> The type of the result (usually List<UserNotificationEntity>).
     * @param condition The search condition DTO.
     * @param options The pagination and count options.
     * @param sort The sorting criteria.
     * @param list The collector to materialize the results.
     * @return The collected result list.
     */
    @Select(strategy = SelectType.COLLECT)
    <R> R selectAll(NotificationRepository.Condition condition, SelectOptions options, NotificationRepository.Sort sort,
        Collector<UserNotificationEntity, ?, R> list);

    /**
     * Selects a single {@link Base005NotificationReadEntity} record based on the user ID and
     * the notification ID. This is typically used before marking a notification as read.
     *
     * @param uid The user ID.
     * @param notificationId The ID of the notification.
     * @return An Optional containing the read status entity.
     */
    @Select
    Optional<Base005NotificationReadEntity> selectUserNotification(String uid, String notificationId);

    /**
     * Selects all {@link Base005NotificationReadEntity} records where the read status is 'unread'
     * for a given user ID. This is used for the 'Read All' functionality.
     *
     * @param uid The user ID.
     * @return A list of unread read status entities.
     */
    @Select
    List<Base005NotificationReadEntity> selectUnreadNotifications(String uid);
}
