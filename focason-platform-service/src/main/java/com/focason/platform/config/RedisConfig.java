// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.config;

import java.time.Duration;
import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;

/**
 * RedisConfig
 * <p>
 * Configuration class for setting up customized Redis components, including
 * {@link RedisTemplate} for direct access and {@link RedisCacheManager} for Spring Cache abstraction.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class RedisConfig
{

    /**
     * Configures the primary {@link RedisTemplate} for performing explicit Redis operations.
     * <p>
     * Sets up key/value serializers and enables session-bound transaction support.
     * </p>
     *
     * @param connectionFactory The connection factory provided by Spring Boot autoconfiguration.
     * @return A customized RedisTemplate instance.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        // Enable transaction support in session-bound mode.
        // This is crucial for using MULTI/EXEC commands within a single connection context.
        connectionFactory.setShareNativeConnection(false); // Ensures a connection is not shared across threads during a
                                                           // transaction.
        connectionFactory.setValidateConnection(true); // Validates connection before use.

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Set serializers for keys and values
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setEnableTransactionSupport(true); // Enable transaction support
        template.afterPropertiesSet();

        return template;
    }

    /**
     * Configures the {@link RedisCacheManager} for Spring's Caching Abstraction.
     * <p>
     * Defines the default cache configuration, including key/value serialization and TTL.
     * </p>
     *
     * @param connectionFactory The connection factory.
     * @return A configured RedisCacheManager instance.
     */
    @Bean
    public RedisCacheManager cacheManager(@NonNull LettuceConnectionFactory connectionFactory) {
        // Define the serialization method for the cache keys and values
        RedisSerializationContext.SerializationPair<String> stringPair =
            RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());

        // Create the default cache configuration
        Duration cacheTtl = Objects.requireNonNull(Duration.ofMinutes(30)); // Set default cache expiration time to 30
                                                                            // minutes
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(cacheTtl)
            .serializeKeysWith(stringPair) // Use String serializer for cache keys
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        // Build the Cache Manager using the connection factory and default configuration
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .build();
    }
}
