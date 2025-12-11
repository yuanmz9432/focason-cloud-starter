// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.eureka;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaApplication
 * <p>
 * This is the main entry point for the Focason Cloud Eureka Server.
 *
 * <p>
 * It is configured with {@code @EnableEurekaServer}, enabling it to function as a
 * service discovery and registration server, centralizing the location of microservices
 * within the entire Focason Cloud infrastructure.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication
{
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
