// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.base;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * FocasonPlatformServiceApplication
 * <p>
 * The main entry point for the Base Service Spring Boot application.
 * <p>
 * This application is configured to:
 * <ul>
 * <li>Register itself as a client with Eureka Discovery Service (via {@link EnableEurekaClient}).</li>
 * <li>Enable Feign Clients for declarative REST communication with other microservices
 * (via {@link EnableFeignClients}).</li>
 * <li>Automatically configure Spring Boot features and component scanning starting from the base package
 * "com.focason" (via {@link SpringBootApplication}).</li>
 * </ul>
 *
 * @author Focason Lab Team
 * @since 0.0.1
 */
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.focason.core.feign")
@SpringBootApplication(scanBasePackages = "com.focason")
public class FocasonPlatformServiceApplication
{
    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FocasonPlatformServiceApplication.class);
        // Allows overriding bean definitions, which can be useful in complex configurations or testing.
        springApplication.setAllowBeanDefinitionOverriding(true);
        SpringApplication.run(FocasonPlatformServiceApplication.class, args);
    }
}
