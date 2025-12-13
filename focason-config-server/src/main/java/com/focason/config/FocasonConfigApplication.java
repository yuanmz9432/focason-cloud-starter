// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * FocasonConfigApplication
 * <p>
 * This class serves as the entry point for the Spring Cloud Config Server application.
 * It is registered as a Eureka client to allow other services to locate it.
 *
 * @since 0.0.1
 * @author yuanmz9432
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class FocasonConfigApplication
{
    /**
     * Main method to run the Spring Boot application.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(FocasonConfigApplication.class, args);
    }
}
