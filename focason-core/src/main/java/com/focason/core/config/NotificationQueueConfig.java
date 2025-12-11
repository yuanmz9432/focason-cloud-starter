// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * NotificationQueueConfigure
 * <p>
 * This configuration defines the queues and exchanges used for handling
 * notification tasks, including support for dead-letter processing.
 *
 * <p>
 * Naming convention:
 * - Queue: notification.send.queue
 * - Exchange: notification.exchange
 * - Routing Key: notification.send
 * <p>
 * Dead letter queue:
 * - When email delivery fails (timeout, exception, retry limit), it will be added to the email.dlq queue.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class NotificationQueueConfig
{
    /***********************************************
     * Main Queue Configuration
     **********************************************/
    // Main Queue
    public static final String NOTIFICATION_SEND_QUEUE = "notification.send.queue";
    // Main Exchange
    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    // Main Routing Key
    public static final String NOTIFICATION_SEND_ROUTING_KEY = "notification.send";

    /***********************************************
     * Dead Letter Queue Configuration
     **********************************************/
    // Dead Letter Queue
    public static final String NOTIFICATION_DLQ = "notification.dlq";
    // Dead Letter Queue Exchange
    public static final String NOTIFICATION_DLQ_EXCHANGE = "notification.dlq.exchange";
    // Dead Letter Queue Routing Key
    public static final String NOTIFICATION_DLQ_ROUTING_KEY = "notification.dlq";

    /***********************************************
     * Exchange Beans
     **********************************************/
    // Main Exchange
    @Bean(NOTIFICATION_EXCHANGE)
    public DirectExchange notificationExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE);
    }

    // Dead Letter Exchange
    @Bean(NOTIFICATION_DLQ_EXCHANGE)
    public DirectExchange notificationDlqExchange() {
        return new DirectExchange(NOTIFICATION_DLQ_EXCHANGE);
    }

    /***********************************************
     * Queue Beans
     * <p>
     * Configure a dead-letter switch so that failed messages are forwarded to DLQ.
     **********************************************/
    // Main Queue
    @Bean(NOTIFICATION_SEND_QUEUE)
    public Queue notificationSendQueue() {
        return QueueBuilder.durable(NOTIFICATION_SEND_QUEUE)
            .withArgument("x-dead-letter-exchange", NOTIFICATION_DLQ_EXCHANGE)
            .withArgument("x-dead-letter-routing-key", NOTIFICATION_DLQ_ROUTING_KEY)
            .build();
    }

    // Dead Letter Queue
    @Bean(NOTIFICATION_DLQ)
    public Queue notificationDlqQueue() {
        return QueueBuilder.durable(NOTIFICATION_DLQ).build();
    }

    /***********************************************
     * Binding Beans
     * <p>
     * Configure a dead-letter switch so that failed messages are forwarded to DLQ.
     **********************************************/
    // Main Queue → Main Exchange
    @Bean
    public Binding emailSendBinding() {
        return BindingBuilder.bind(notificationSendQueue())
            .to(notificationExchange())
            .with(NOTIFICATION_SEND_ROUTING_KEY);
    }

    // Dead Letter Queue → Dead Letter Queue
    @Bean
    public Binding emailDlqBinding() {
        return BindingBuilder.bind(notificationDlqQueue())
            .to(notificationDlqExchange())
            .with(NOTIFICATION_SEND_ROUTING_KEY);
    }
}
