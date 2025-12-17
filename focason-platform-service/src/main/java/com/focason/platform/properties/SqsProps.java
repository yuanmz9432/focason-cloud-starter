package com.focason.platform.properties;

import lombok.Data;

/**
 * AWS SQS Configuration Properties
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class SqsProps
{
    /**
     * SQS Queue URL
     */
    private String queueUrl;

    /**
     * Maximum number of messages to retrieve in a single request (1-10)
     */
    private Integer maxNumberOfMessages = 10;

    /**
     * Wait time for long polling in seconds (0-20)
     */
    private Integer waitTimeSeconds = 20;

    /**
     * Visibility timeout in seconds (how long a message is invisible after being received)
     */
    private Integer visibilityTimeout = 30;

    /**
     * Message retention period in seconds (60 seconds to 14 days)
     */
    private Integer messageRetentionPeriod = 345600; // 4 days

    /**
     * Enable dead letter queue
     */
    private Boolean enableDeadLetterQueue = false;

    /**
     * Dead letter queue URL
     */
    private String deadLetterQueueUrl;

    /**
     * Maximum receive count before sending to dead letter queue
     */
    private Integer maxReceiveCount = 3;
}
