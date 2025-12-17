// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.aws.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

/**
 * Service class for interacting with AWS SQS (Simple Queue Service).
 * Provides convenient methods for sending, receiving, and managing messages.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
public class SqsService
{

    private static final Logger logger = LoggerFactory.getLogger(SqsService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final SqsClient sqsClient;
    private String queueUrl;
    private Integer maxNumberOfMessages;
    private Integer waitTimeSeconds;
    private Integer visibilityTimeout;

    /**
     * Send a simple text message to the queue.
     *
     * @param messageBody The message content
     * @return Message ID if successful
     */
    public Optional<String> sendMessage(String messageBody) {
        return sendMessage(messageBody, null, null);
    }

    /**
     * Send a message with optional delay and attributes.
     *
     * @param messageBody The message content
     * @param delaySeconds Delay before the message becomes available (0-900 seconds)
     * @param messageAttributes Optional message attributes
     * @return Message ID if successful
     */
    public Optional<String> sendMessage(String messageBody, Integer delaySeconds,
        Map<String, MessageAttributeValue> messageAttributes) {
        try {
            SendMessageRequest.Builder requestBuilder = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody);

            if (delaySeconds != null) {
                requestBuilder.delaySeconds(delaySeconds);
            }

            if (messageAttributes != null && !messageAttributes.isEmpty()) {
                requestBuilder.messageAttributes(messageAttributes);
            }

            SendMessageResponse response = sqsClient.sendMessage(requestBuilder.build());
            logger.info("Message sent successfully. MessageId: {}", response.messageId());
            return Optional.of(response.messageId());
        } catch (SqsException e) {
            logger.error("Failed to send message to queue: {}", queueUrl, e);
            return Optional.empty();
        }
    }

    /**
     * Send an object as JSON message.
     *
     * @param object The object to send
     * @param delaySeconds Optional delay
     * @return Message ID if successful
     */
    public Optional<String> sendObject(Object object, Integer delaySeconds) {
        try {
            String json = objectMapper.writeValueAsString(object);
            return sendMessage(json, delaySeconds, null);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize object to JSON", e);
            return Optional.empty();
        }
    }

    /**
     * Send an object as JSON message (no delay).
     *
     * @param object The object to send
     * @return Message ID if successful
     */
    public Optional<String> sendObject(Object object) {
        return sendObject(object, null);
    }

    /**
     * Send multiple messages in a batch (up to 10 messages).
     *
     * @param messages List of message bodies
     * @return Map of message IDs (entry ID -> message ID)
     */
    public Map<String, String> sendBatchMessages(List<String> messages) {
        if (messages == null || messages.isEmpty() || messages.size() > 10) {
            logger.error("Batch size must be between 1 and 10. Provided: {}",
                messages != null ? messages.size() : 0);
            return Map.of();
        }

        try {
            List<SendMessageBatchRequestEntry> entries = messages.stream()
                .map(msg -> SendMessageBatchRequestEntry.builder()
                    .id(String.valueOf(messages.indexOf(msg)))
                    .messageBody(msg)
                    .build())
                .collect(Collectors.toList());

            SendMessageBatchRequest batchRequest = SendMessageBatchRequest.builder()
                .queueUrl(queueUrl)
                .entries(entries)
                .build();

            SendMessageBatchResponse response = sqsClient.sendMessageBatch(batchRequest);

            Map<String, String> messageIds = new HashMap<>();
            response.successful().forEach(entry -> messageIds.put(entry.id(), entry.messageId()));

            if (!response.failed().isEmpty()) {
                logger.warn("Some messages failed to send. Failed count: {}", response.failed().size());
                response.failed().forEach(entry -> logger.error("Failed message ID: {}, Code: {}, Message: {}",
                    entry.id(), entry.code(), entry.message()));
            }

            return messageIds;
        } catch (SqsException e) {
            logger.error("Failed to send batch messages", e);
            return Map.of();
        }
    }

    /**
     * Receive messages from the queue.
     *
     * @return List of messages
     */
    public List<Message> receiveMessages() {
        return receiveMessages(maxNumberOfMessages, waitTimeSeconds);
    }

    /**
     * Receive messages with custom parameters.
     *
     * @param maxMessages Maximum number of messages to receive (1-10)
     * @param waitTimeSeconds Long polling wait time (0-20 seconds)
     * @return List of messages
     */
    public List<Message> receiveMessages(Integer maxMessages, Integer waitTimeSeconds) {
        try {
            ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(maxMessages != null ? maxMessages : this.maxNumberOfMessages)
                .waitTimeSeconds(waitTimeSeconds != null ? waitTimeSeconds : this.waitTimeSeconds)
                .visibilityTimeout(visibilityTimeout)
                .messageAttributeNames("All")
                .build();

            ReceiveMessageResponse response = sqsClient.receiveMessage(receiveRequest);
            logger.debug("Received {} messages from queue", response.messages().size());
            return response.messages();
        } catch (SqsException e) {
            logger.error("Failed to receive messages from queue: {}", queueUrl, e);
            return List.of();
        }
    }

    /**
     * Receive and deserialize messages as objects.
     *
     * @param clazz The class type to deserialize to
     * @param <T> The type parameter
     * @return List of deserialized objects with their receipt handles
     */
    public <T> List<SqsMessageWrapper<T>> receiveObjects(Class<T> clazz) {
        List<Message> messages = receiveMessages();
        return messages.stream()
            .map(msg -> {
                try {
                    T object = objectMapper.readValue(msg.body(), clazz);
                    return new SqsMessageWrapper<>(object, msg.receiptHandle());
                } catch (JsonProcessingException e) {
                    logger.error("Failed to deserialize message: {}", msg.messageId(), e);
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * Delete a message from the queue.
     *
     * @param receiptHandle The receipt handle of the message
     * @return true if successful
     */
    public boolean deleteMessage(String receiptHandle) {
        try {
            DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();

            sqsClient.deleteMessage(deleteRequest);
            logger.debug("Message deleted successfully");
            return true;
        } catch (SqsException e) {
            logger.error("Failed to delete message", e);
            return false;
        }
    }

    /**
     * Delete multiple messages in a batch (up to 10 messages).
     *
     * @param receiptHandles List of receipt handles
     * @return Number of successfully deleted messages
     */
    public int deleteBatchMessages(List<String> receiptHandles) {
        if (receiptHandles == null || receiptHandles.isEmpty() || receiptHandles.size() > 10) {
            logger.error("Batch size must be between 1 and 10. Provided: {}",
                receiptHandles != null ? receiptHandles.size() : 0);
            return 0;
        }

        try {
            List<DeleteMessageBatchRequestEntry> entries = receiptHandles.stream()
                .map(handle -> DeleteMessageBatchRequestEntry.builder()
                    .id(String.valueOf(receiptHandles.indexOf(handle)))
                    .receiptHandle(handle)
                    .build())
                .collect(Collectors.toList());

            DeleteMessageBatchRequest batchRequest = DeleteMessageBatchRequest.builder()
                .queueUrl(queueUrl)
                .entries(entries)
                .build();

            DeleteMessageBatchResponse response = sqsClient.deleteMessageBatch(batchRequest);

            if (!response.failed().isEmpty()) {
                logger.warn("Some messages failed to delete. Failed count: {}", response.failed().size());
            }

            return response.successful().size();
        } catch (SqsException e) {
            logger.error("Failed to delete batch messages", e);
            return 0;
        }
    }

    /**
     * Change message visibility timeout.
     *
     * @param receiptHandle The receipt handle
     * @param visibilityTimeout New visibility timeout in seconds
     * @return true if successful
     */
    public boolean changeMessageVisibility(String receiptHandle, int visibilityTimeout) {
        try {
            ChangeMessageVisibilityRequest request = ChangeMessageVisibilityRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .visibilityTimeout(visibilityTimeout)
                .build();

            sqsClient.changeMessageVisibility(request);
            logger.debug("Message visibility changed successfully");
            return true;
        } catch (SqsException e) {
            logger.error("Failed to change message visibility", e);
            return false;
        }
    }

    /**
     * Get approximate number of messages in the queue.
     *
     * @return Map of queue attributes
     */
    public Map<QueueAttributeName, String> getQueueAttributes() {
        try {
            GetQueueAttributesRequest request = GetQueueAttributesRequest.builder()
                .queueUrl(queueUrl)
                .attributeNames(QueueAttributeName.ALL)
                .build();

            GetQueueAttributesResponse response = sqsClient.getQueueAttributes(request);
            return response.attributes();
        } catch (SqsException e) {
            logger.error("Failed to get queue attributes", e);
            return Map.of();
        }
    }

    /**
     * Get approximate number of messages available.
     *
     * @return Number of messages
     */
    public int getApproximateNumberOfMessages() {
        Map<QueueAttributeName, String> attributes = getQueueAttributes();
        return Integer.parseInt(attributes.getOrDefault(QueueAttributeName.APPROXIMATE_NUMBER_OF_MESSAGES, "0"));
    }

    /**
     * Purge all messages from the queue (use with caution!).
     *
     * @return true if successful
     */
    public boolean purgeQueue() {
        try {
            PurgeQueueRequest request = PurgeQueueRequest.builder()
                .queueUrl(queueUrl)
                .build();

            sqsClient.purgeQueue(request);
            logger.warn("Queue purged: {}", queueUrl);
            return true;
        } catch (SqsException e) {
            logger.error("Failed to purge queue", e);
            return false;
        }
    }

    /**
     * Wrapper class for deserialized SQS messages.
     *
     * @param <T> The message body type
     */
    public record SqsMessageWrapper<T>(
    T body, String receiptHandle)
    {
    }
}
