// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.eureka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfig
 * <p>
 * Configuration class for defining the security policies for the Eureka Discovery Server.
 *
 * <p>
 * For the service discovery endpoints (e.g., '/eureka/**'), security mechanisms
 * such as CSRF, Basic Auth, and Form Login are explicitly disabled. This is necessary
 * to allow microservices to register and communicate with the Eureka server without
 * requiring explicit credentials for machine-to-machine interaction.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{
    /**
     * Defines the security filter chain for the Eureka server.
     *
     * <p>
     * This configuration disables standard security features required for browser-based
     * interactions (Basic Auth, Form Login, and CSRF protection) to ensure seamless
     * registration and heartbeating of client microservices.
     * </p>
     *
     * @param httpSecurity The {@link HttpSecurity} object to configure web security.
     * @return A configured {@link SecurityFilterChain} instance.
     * @throws Exception If an error occurs during security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic().disable() // Disables Basic Authentication
            .formLogin().disable() // Disables form-based login
            .csrf().disable() // Disables CSRF protection (essential for non-browser clients like Eureka clients)
            .build();
    }
}
