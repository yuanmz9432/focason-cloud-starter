// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.consumer;

import com.focason.core.domain.NotificationType;
import com.focason.core.domain.Switch;
import com.focason.core.entity.Base004NotificationEntity;
import com.focason.core.entity.Base005NotificationReadEntity;
import com.focason.core.feign.UserFeignClient;
import com.focason.core.resource.NotificationResource;
import com.focason.core.utility.FsUtilityToolkit;
import com.focason.platform.notification.repository.NotificationRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * NotificationConsumer
 * <p>
 * Message consumer that listens to the notification queue and handles persistence
 * and real-time delivery (via WebSocket/STOMP) of notifications to users.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationSqsConsumer
{
    final Logger logger = LoggerFactory.getLogger(NotificationSqsConsumer.class);
    public static final String QUEUE_NAME = "focason-notification-send-queue";

    /** Spring's utility for sending messages to STOMP destinations. */
    private final SimpMessagingTemplate messagingTemplate;

    /** Repository for saving notification and read status entities. */
    private final NotificationRepository notificationRepository;

    /** Feign client for fetching user-related data (e.g., active user IDs). */
    private final UserFeignClient userFeignClient;

    /**
     * Consumes messages from the dedicated notification sending queue.
     * <p>
     * It handles three main steps: 1. Persistence, 2. Initialization of read status, 3. Real-time delivery.
     * </p>
     *
     * @param resource The notification resource containing content and target information.
     */
    @SqsListener(QUEUE_NAME)
    public void consumeMessage(NotificationResource resource) {

        logger.info("Receive a notification message: {}", resource);

        // 1. Prepare and Persist Notification Entity
        var notificationId = FsUtilityToolkit.generateUUID();
        resource.setNotificationId(notificationId);
        resource.setNotifyAt(LocalDateTime.now());

        // 插入通知主体 (Insert main notification entity)
        notificationRepository.save(FsUtilityToolkit.convert(resource, Base004NotificationEntity.class));

        // 2. Determine Targets and Initialize Read Status
        List<Base005NotificationReadEntity> notificationReadEntities = null;

        switch (NotificationType.of(resource.getType())) {
            case BROADCAST:
                // 广播类型: 获取所有有效用户
                var response = userFeignClient.getActiveUserIds();

                // 确保响应体和用户列表非空
                if (Objects.requireNonNull(response.getBody()).activeUids() != null
                    && !Objects.requireNonNull(Objects.requireNonNull(response.getBody()).activeUids()).isEmpty()) {
                    notificationReadEntities =
                        getBase005NotificationReadEntities(Objects.requireNonNull(Objects.requireNonNull(response.getBody()).activeUids()), notificationId);
                }

                if (notificationReadEntities != null) {
                    // 循环user，插入通知已读记录
                    notificationRepository.save(notificationReadEntities);
                }

                // 发送 WebSocket 广播消息到公共 Topic
                messagingTemplate.convertAndSend("/topic/notifications", resource);
                logger.info("Broadcast notification sent successfully to {} users via WebSocket.",
                    notificationReadEntities != null ? notificationReadEntities.size() : 0);
                break;

            case UNICAST:
                // 单播类型: 使用 resource 中 targets 字段指定的用户
                notificationReadEntities =
                    getBase005NotificationReadEntities(List.of(resource.getTargets()), notificationId);

                // 插入通知已读记录
                notificationRepository.save(notificationReadEntities);

                // 循环发送点对点 WebSocket 消息
                notificationReadEntities.forEach(notificationReadEntity -> {
                    // DEBUG: 降低日志级别，避免大量单播时的日志风暴
                    logger.debug("Sending UNICAST notification to UID: {}", notificationReadEntity.getUid());
                    // 发送 WebSocket 消息到用户专属队列
                    messagingTemplate.convertAndSendToUser(Objects.requireNonNull(notificationReadEntity.getUid()), "/queue/notifications",
                        resource);
                });
                logger.info("UNICAST notification sent successfully to {} targets.", notificationReadEntities.size());
                break;
            default:
                logger.warn("Received notification with unknown or unhandled type: {}", resource.getType());
        }
    }

    /**
     * Helper method to convert a list of user IDs into a list of
     * {@link Base005NotificationReadEntity} instances, setting the initial status to unread.
     *
     * @param activeUids The list of user unique identifiers.
     * @param notificationId The ID of the parent notification.
     * @return A list of new NotificationRead entities.
     */
    @NotNull
    private static ArrayList<Base005NotificationReadEntity> getBase005NotificationReadEntities(
        @NotNull List<String> activeUids, String notificationId) {
        var notificationReadEntities = new ArrayList<Base005NotificationReadEntity>();

        activeUids.forEach(uid -> {
            var entity = new Base005NotificationReadEntity();
            entity.setNotificationId(notificationId);
            entity.setUid(uid);
            entity.setIsRead(Switch.OFF.getValue()); // Set initial status to 'Unread'
            notificationReadEntities.add(entity);
        });

        return notificationReadEntities;
    }
}
