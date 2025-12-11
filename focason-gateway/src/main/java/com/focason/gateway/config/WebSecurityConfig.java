// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway.config;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebSecurityConfig
 * <p>
 * Configuration class for defining the security policies for the Spring Cloud Gateway.
 *
 * <p>
 * Since Spring Cloud Gateway is built on Spring WebFlux (Reactive Stack), this configuration
 * uses {@code ServerHttpSecurity} to apply non-blocking security filters. Its primary
 * role is to configure security and necessary features like CORS for all incoming
 * external requests before routing them to downstream services.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.security.config.web.server.ServerHttpSecurity
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__({
    @Autowired, @Lazy
}))
public class WebSecurityConfig implements WebMvcConfigurer
{

    /**
     * Defines the reactive security filter chain for the Gateway.
     *
     * <p>
     * The configuration currently disables Basic Authentication, Form Login, and CSRF
     * protection, while explicitly enabling CORS for front-end integration.
     * </p>
     *
     * @param http The reactive {@link ServerHttpSecurity} object to configure web security.
     * @return A configured {@link SecurityWebFilterChain} instance for the Reactive stack.
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.httpBasic().disable() // Disables Basic Auth
            .cors(Customizer.withDefaults()) // Enables CORS
            .formLogin().disable() // Disables form-based login
            .csrf().disable() // Disables CSRF protection
            .build();
    }
}
