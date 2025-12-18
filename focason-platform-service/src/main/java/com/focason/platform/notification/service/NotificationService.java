// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.notification.service;


import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.domain.Switch;
import com.focason.core.resource.UserNotificationResource;
import com.focason.core.utility.FsUtilityToolkit;
import com.focason.platform.notification.repository.NotificationRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * NotificationService
 * <p>
 * Service layer for managing user notifications, including searching and updating
 * the read status. All modifying operations are marked as transactional.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationService
{

    final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    /** Repository for accessing and manipulating notification data. */
    private final NotificationRepository repository;

    /**
     * Searches for user notifications based on specified conditions, pagination, and sorting.
     *
     * @param condition The criteria for filtering notifications (e.g., user ID, read status).
     * @param pagination The pagination settings (page number, page size).
     * @param sort The sorting criteria.
     * @return A result set containing paginated {@link UserNotificationResource} and total count.
     */
    @Transactional
    public FsResultSet<UserNotificationResource> search(NotificationRepository.Condition condition,
        FsPagination pagination,
        NotificationRepository.Sort sort) {

        // Find notifications from the repository based on combined criteria.
        var resultSet = repository.findAll(condition, pagination, sort);

        // Convert the found entities to the transfer resource type.
        var notifications = FsUtilityToolkit.convert(resultSet.getData(), UserNotificationResource.class);

        return new FsResultSet<>(notifications, resultSet.getCount());
    }

    /**
     * Marks a specific notification as read for a given user.
     *
     * @param uid The unique identifier of the user.
     * @param notificationId The ID of the notification to be marked as read.
     * @throws RuntimeException if the notification/read record for the user is not found.
     */
    @Transactional
    public void read(String uid, String notificationId) {
        // Retrieve the notification read entity for the specific user and notification ID.
        var notification = repository.findNotification(uid, notificationId).orElseThrow(
            () -> new RuntimeException(
                String.format("Notification with id %s not found for user %s", notificationId, uid)));

        // Update read status and timestamp.
        notification.setIsRead(Switch.ON.getValue());
        notification.setReadAt(LocalDateTime.now());

        // Persist the updated entity.
        repository.update(notification);
        logger.info("Notification {} marked as read by user {}.", notificationId, uid);
    }

    /**
     * Marks all unread notifications as read for a given user in a single transaction.
     *
     * @param uid The unique identifier of the user.
     */
    @Transactional
    public void readAll(String uid) {
        // Retrieve all unread notification entities for the user.
        var notifications = repository.findUnreadNotifications(uid);

        if (notifications != null && !notifications.isEmpty()) {
            // Bulk update the entities in memory.
            notifications.forEach(entity -> {
                entity.setIsRead(Switch.ON.getValue());
                entity.setReadAt(LocalDateTime.now()); // Record the time of bulk read
            });

            // Persist all updated entities (likely triggering a batch update).
            repository.update(notifications);
            logger.info("Marked {} unread notifications as read for user {}.", notifications.size(), uid);
        } else {
            logger.info("No unread notifications found for user {}.", uid);
        }
    }
}
