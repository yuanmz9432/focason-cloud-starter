// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.consumer;

import com.focason.platform.aws.service.SqsService;
import com.focason.platform.aws.service.SqsServiceManager;
import io.awspring.cloud.sqs.annotation.SqsListener;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

/**
 * Email SQS Consumer
 * <p>
 * Consumes messages from AWS SQS email-send-queue and processes them.
 * This consumer polls the queue at regular intervals and processes received messages.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class EmailSqsConsumer
{
    private static final Logger logger = LoggerFactory.getLogger(EmailSqsConsumer.class);
    private static final String QUEUE_NAME = "focason-email-send-queue";

    private final SqsService sqsService;
    private final SqsServiceManager sqsServiceManager;


    @SqsListener(QUEUE_NAME)
    public void listen(Object task) {
        logger.info("接收到任务: {}", task.toString());
        // 执行业务逻辑
    }
    /**
     * Automatically polls the email-send-queue every 5 seconds.
     * You can adjust the polling interval by changing the fixedDelay value.
     */
    // @Scheduled(fixedDelay = 5000) // Poll every 5 seconds
    // @SqsListener(QUEUE_NAME)
    // public void consumeEmailMessages() {
    // sqsServiceManager.getQueueUrl(QUEUE_NAME)
    // .ifPresentOrElse(
    // queueUrl -> {
    // List<Message> messages = sqsService.receiveMessages(queueUrl);
    // if (!messages.isEmpty()) {
    // logger.info("Received {} message(s) from email-send-queue", messages.size());
    // }
    //
    // messages.forEach(message -> {
    // try {
    // logger.info("Processing message from email-send-queue:");
    // logger.info(" MessageId: {}", message.messageId());
    // logger.info(" Body: {}", message.body());
    // logger.info(" Attributes: {}", message.attributes());
    //
    // // TODO: Process your message here
    // // Example: Parse JSON and handle email sending logic
    // // EmailResource emailResource = objectMapper.readValue(message.body(),
    // // EmailResource.class);
    // // processEmail(emailResource);
    //
    // // Delete message after successful processing
    // boolean deleted = sqsService.deleteMessage(message.receiptHandle(), queueUrl);
    // if (deleted) {
    // logger.info("Message {} deleted successfully", message.messageId());
    // } else {
    // logger.warn("Failed to delete message {}", message.messageId());
    // }
    // } catch (Exception e) {
    // logger.error("Error processing message {}: {}", message.messageId(), e.getMessage(), e);
    // // Message will become visible again after visibility timeout
    // // You may want to implement retry logic or send to DLQ here
    // }
    // });
    // },
    // () -> logger.warn("Queue '{}' not found in configuration", QUEUE_NAME));
    // }

    /**
     * Manually receive messages from the queue.
     * This method can be called from a REST endpoint or other services.
     *
     * @return List of received messages
     */
    public List<Message> receiveMessages() {
        return sqsServiceManager.getQueueUrl(QUEUE_NAME)
            .map(queueUrl -> {
                List<Message> messages = sqsService.receiveMessages(queueUrl);
                logger.info("Manually received {} message(s) from email-send-queue", messages.size());
                return messages;
            })
            .orElseGet(() -> {
                logger.warn("Queue '{}' not found in configuration", QUEUE_NAME);
                return List.of();
            });
    }

    /**
     * Manually receive and process a single message.
     * Useful for testing or manual triggers.
     */
    public void processSingleMessage() {
        sqsServiceManager.getQueueUrl(QUEUE_NAME)
            .ifPresent(queueUrl -> {
                List<Message> messages = sqsService.receiveMessages(queueUrl);
                if (!messages.isEmpty()) {
                    Message message = messages.get(0);
                    logger.info("Processing single message: {}", message.body());
                    // Process message...
                    sqsService.deleteMessage(message.receiptHandle(), queueUrl);
                } else {
                    logger.info("No messages available in email-send-queue");
                }
            });
    }
}
