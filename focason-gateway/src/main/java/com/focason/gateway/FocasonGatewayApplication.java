// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * FocasonGatewayApplication
 * <p>
 * This is the main entry point for the Focason Cloud Gateway Server.
 *
 * <p>
 * As the API Gateway, it acts as a single point of entry for all client requests,
 * handling routing, security (authentication/authorization), and cross-cutting concerns.
 * It is configured as a Eureka client to register itself with the discovery server
 * and uses Feign clients to communicate with other services (e.g., Auth Service for validation).
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.cloud.openfeign.EnableFeignClients
 */
@EnableFeignClients(basePackages = "com.focason.core.feign")
@SpringBootApplication
public class FocasonGatewayApplication
{
    public static void main(String[] args) {
        SpringApplication.run(FocasonGatewayApplication.class, args);
    }
}
