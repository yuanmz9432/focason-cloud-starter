// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.config;



import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileImportQueue
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class FileImportQueueConfig
{

    // Normal queue configuration
    public static final String NORMAL_QUEUE = "file-import-queue";
    public static final String NORMAL_EXCHANGE = "file-import-exchange";
    public static final String NORMAL_ROUTING_KEY = "file-import-routing-key";
    // Dead letter queue configuration
    public static final String DLX_QUEUE = "file-import-dlx-queue";
    public static final String DLX_EXCHANGE = "file-import-dlx-exchange";
    public static final String DLX_ROUTING_KEY = "file-import-dlx-routing-key";

    /**
     * Normal queue
     */
    @Bean(NORMAL_EXCHANGE)
    public DirectExchange normalExchange() {
        return new DirectExchange(NORMAL_EXCHANGE);
    }

    /**
     * Dead letter queue
     */
    @Bean(DLX_EXCHANGE)
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    /**
     * Normal queue, bound to dead letter switch
     */
    @Bean(NORMAL_QUEUE)
    public Queue normalQueue() {
        return QueueBuilder.durable(NORMAL_QUEUE)
            .withArgument("x-dead-letter-exchange", DLX_EXCHANGE) // 指定死信交换机
            .withArgument("x-dead-letter-routing-key", DLX_ROUTING_KEY) // 指定死信队列的路由键
            .build();
    }

    /**
     * Dead letter queue
     */
    @Bean(DLX_QUEUE)
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DLX_QUEUE).build();
    }

    /**
     * Normal queue binding
     */
    @Bean(NORMAL_ROUTING_KEY)
    public Binding normalBinding() {
        return BindingBuilder.bind(normalQueue())
            .to(normalExchange())
            .with(NORMAL_ROUTING_KEY);
    }

    /**
     * Dead letter queue binding
     */
    @Bean(DLX_ROUTING_KEY)
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
            .to(deadLetterExchange())
            .with(DLX_ROUTING_KEY);
    }
}
