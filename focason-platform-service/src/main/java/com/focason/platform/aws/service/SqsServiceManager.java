// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.aws.service;

import com.focason.platform.properties.CloudProps;
import com.focason.platform.properties.SqsProps;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Manager for SQS queue configurations.
 * Provides convenient methods to get queue URLs and properties by queue name.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class SqsServiceManager
{
    private final CloudProps cloudProps;

    /**
     * Get queue URL by queue name.
     *
     * @param queueName The name of the queue (as defined in configuration)
     * @return Optional queue URL
     */
    public Optional<String> getQueueUrl(String queueName) {
        return getQueueProps(queueName)
            .map(SqsProps::getQueueUrl);
    }

    /**
     * Get queue properties by queue name.
     *
     * @param queueName The name of the queue (as defined in configuration)
     * @return Optional queue properties
     */
    public Optional<SqsProps> getQueueProps(String queueName) {
        Map<String, SqsProps> sqsMap = cloudProps.getAws().getSqs();
        if (sqsMap == null || sqsMap.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(sqsMap.get(queueName));
    }

    /**
     * Get all configured queue names.
     *
     * @return Set of queue names
     */
    public java.util.Set<String> getAllQueueNames() {
        Map<String, SqsProps> sqsMap = cloudProps.getAws().getSqs();
        if (sqsMap == null || sqsMap.isEmpty()) {
            return java.util.Set.of();
        }
        return sqsMap.keySet();
    }

    /**
     * Check if a queue exists in configuration.
     *
     * @param queueName The name of the queue
     * @return true if the queue exists
     */
    public boolean queueExists(String queueName) {
        return getQueueProps(queueName).isPresent();
    }
}
