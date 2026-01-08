// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.notification.controller;

import java.util.Objects;
import com.focason.core.annotation.FsConditionParam;
import com.focason.core.annotation.FsPaginationParam;
import com.focason.core.annotation.FsSortParam;
import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.attribute.FsSort;
import com.focason.core.request.NotificationRequest;
import com.focason.core.resource.NotificationResource;
import com.focason.core.response.NotificationSearchResponse;
import com.focason.core.utility.FsUtilityToolkit;
import com.focason.platform.consumer.NotificationSqsConsumer;
import com.focason.platform.notification.repository.NotificationRepository;
import com.focason.platform.notification.service.NotificationService;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * NotificationController
 * <p>
 * REST Controller for handling notification operations, including dispatching,
 * searching, and managing the read status of user notifications.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RefreshScope // Allows configuration properties to be refreshed without restarting the application
@AllArgsConstructor(onConstructor = @__({
    @Autowired
}))
public class NotificationController
{

    /** RabbitTemplate for sending asynchronous messages to the notification queue. */
    private final SqsTemplate sqsTemplate;

    /** Service layer for business logic related to notification search and read status management. */
    private final NotificationService service;

    // --- API Endpoints URLs ---
    private final String BROADCAST_URL = "/api/v1/notifications/broadcast";
    private final String SEND_TO_USER_URL = "/api/v1/notifications/users";
    private final String USER_NOTIFICATION_URL = "/api/v1/notifications/user";
    private final String READ_NOTIFICATION_URL = "/api/v1/notifications/{notificationId}";
    private final String READ_ALL_NOTIFICATION_URL = "/api/v1/notifications/read-all";

    /**
     * Dispatches a notification message to be broadcast to all relevant users asynchronously.
     * <p>
     * This endpoint serializes the request and sends it to a RabbitMQ Exchange.
     * The actual processing (persistence and WebSocket delivery) is handled by
     * {@link NotificationSqsConsumer}.
     * </p>
     *
     * @param request The notification content and details.
     * @return 204 No Content, indicating the dispatch was successful.
     */
    @RequestMapping(method = RequestMethod.POST, value = BROADCAST_URL)
    public ResponseEntity<Void> broadcast(@RequestBody NotificationRequest request) {
        // Write a message to RabbitMQ.
        NotificationResource resource = Objects.requireNonNull(
            FsUtilityToolkit.convert(request, NotificationResource.class));
        sqsTemplate.send(NotificationSqsConsumer.QUEUE_NAME, resource);
        return ResponseEntity.noContent().build();
    }

    /**
     * Dispatches a notification message to specified users (unicast) asynchronously.
     * <p>
     * This method sends the request to the RabbitMQ Exchange for asynchronous processing.
     * </p>
     *
     * @param request The notification content, including the target users (UIDs) in the resource.
     * @return 204 No Content, indicating the dispatch was successful.
     */
    @RequestMapping(method = RequestMethod.POST, value = SEND_TO_USER_URL)
    public ResponseEntity<Void> sendToUsers(@RequestBody NotificationRequest request) {
        // Write a message to RabbitMQ.
        NotificationResource resource = Objects.requireNonNull(
            FsUtilityToolkit.convert(request, NotificationResource.class));
        sqsTemplate.send(NotificationSqsConsumer.QUEUE_NAME, resource);
        return ResponseEntity.noContent().build();
    }

    /**
     * Searches notifications intended for the current authenticated user.
     * <p>
     * Automatically extracts the current user's UID from the MDC (Mapped Diagnostic Context),
     * which was previously populated by the security context repository.
     * </p>
     *
     * @param condition The custom search conditions.
     * @param pagination The pagination parameters (page, size).
     * @param fsSort The sorting parameters.
     * @return The result set containing paginated notifications.
     */
    @RequestMapping(method = RequestMethod.GET, value = USER_NOTIFICATION_URL)
    public ResponseEntity<FsResultSet<NotificationSearchResponse>> search(
        @FsConditionParam NotificationRepository.Condition condition,
        @FsPaginationParam FsPagination pagination,
        @FsSortParam(allowedValues = {}) FsSort fsSort) {

        // Use default condition if none is provided
        if (condition == null) {
            condition = NotificationRepository.Condition.DEFAULT;
        }

        // Convert FsSort generic sort parameters to the repository's specific sort type
        var sort = NotificationRepository.Sort.fromFsSort(fsSort);

        // Retrieve the current user ID (UID) from MDC and add it to the search condition
        String currentUid = MDC.get(FsUtilityToolkit.CLAIMS_SUB);
        var notifications = service.search(condition.withUid(currentUid), pagination, sort);

        // Convert entity list to resource response list and return the result set
        return ResponseEntity.ok(
            new FsResultSet<>(FsUtilityToolkit.convert(notifications.getData(), NotificationSearchResponse.class),
                notifications.getCount()));
    }

    /**
     * Marks a specific notification as read for the current user.
     * <p>
     * The current user's UID is automatically retrieved from MDC.
     * </p>
     *
     * @param notificationId The ID of the notification to be marked as read.
     * @return 204 No Content.
     */
    @RequestMapping(method = RequestMethod.PUT, value = READ_NOTIFICATION_URL)
    public ResponseEntity<Void> read(@PathVariable String notificationId) {
        // Retrieve current UID from MDC
        service.read(MDC.get(FsUtilityToolkit.CLAIMS_SUB), notificationId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Marks all unread notifications as read for the current user.
     * <p>
     * The current user's UID is automatically retrieved from MDC.
     * </p>
     * * @return 204 No Content.
     */
    @RequestMapping(method = RequestMethod.PUT, value = READ_ALL_NOTIFICATION_URL)
    public ResponseEntity<Void> readAll() {
        // Retrieve current UID from MDC
        service.readAll(MDC.get(FsUtilityToolkit.CLAIMS_SUB));
        return ResponseEntity.noContent().build();
    }
}
