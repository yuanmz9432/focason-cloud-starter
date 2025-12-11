// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EmailQueueConfigure
 * <p>
 * This configuration defines the queues and exchanges used for handling
 * email-related notification tasks, including support for dead-letter processing.
 *
 * <p>
 * Naming convention:
 * - Queue: email.send.queue
 * - Exchange: email.exchange
 * - Routing Key: email.send
 * <p>
 * Dead letter queue:
 * - When email delivery fails (timeout, exception, retry limit), it will be added to the email.dlq queue.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class EmailQueueConfig
{
    /***********************************************
     * Main Queue Configuration
     **********************************************/
    // Main Queue
    public static final String EMAIL_SEND_QUEUE = "email.send.queue";
    // Main Exchange
    public static final String EMAIL_EXCHANGE = "email.exchange";
    // Main Routing Key
    public static final String EMAIL_SEND_ROUTING_KEY = "email.send";

    /***********************************************
     * Dead Letter Queue Configuration
     **********************************************/
    // Dead Letter Queue
    public static final String EMAIL_DLQ = "email.dlq";
    // Dead Letter Queue Exchange
    public static final String EMAIL_DLQ_EXCHANGE = "email.dlq.exchange";
    // Dead Letter Queue Routing Key
    public static final String EMAIL_DLQ_ROUTING_KEY = "email.dlq";

    /***********************************************
     * Exchange Beans
     **********************************************/
    // Main Exchange
    @Bean(EMAIL_EXCHANGE)
    public DirectExchange emailExchange() {
        return new DirectExchange(EMAIL_EXCHANGE);
    }

    // Dead Letter Exchange
    @Bean(EMAIL_DLQ_EXCHANGE)
    public DirectExchange emailDlqExchange() {
        return new DirectExchange(EMAIL_DLQ_EXCHANGE);
    }

    /***********************************************
     * Queue Beans
     * <p>
     * Configure a dead-letter switch so that failed messages are forwarded to DLQ.
     **********************************************/
    // Main Queue
    @Bean(EMAIL_SEND_QUEUE)
    public Queue emailSendQueue() {
        return QueueBuilder.durable(EMAIL_SEND_QUEUE)
            .withArgument("x-dead-letter-exchange", EMAIL_DLQ_EXCHANGE)
            .withArgument("x-dead-letter-routing-key", EMAIL_DLQ_ROUTING_KEY)
            .build();
    }

    // Dead Letter Queue
    @Bean(EMAIL_DLQ)
    public Queue emailDlqQueue() {
        return QueueBuilder.durable(EMAIL_DLQ).build();
    }

    /***********************************************
     * Binding Beans
     * <p>
     * Configure a dead-letter switch so that failed messages are forwarded to DLQ.
     **********************************************/
    // Main Queue → Main Exchange
    @Bean
    public Binding emailSendBinding() {
        return BindingBuilder.bind(emailSendQueue())
            .to(emailExchange())
            .with(EMAIL_SEND_ROUTING_KEY);
    }

    // Dead Letter Queue → Dead Letter Queue
    @Bean
    public Binding emailDlqBinding() {
        return BindingBuilder.bind(emailDlqQueue())
            .to(emailDlqExchange())
            .with(EMAIL_DLQ_ROUTING_KEY);
    }
}
