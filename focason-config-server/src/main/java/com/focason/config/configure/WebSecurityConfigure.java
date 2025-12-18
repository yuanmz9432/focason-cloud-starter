// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.config.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfigure
 * <p>
 * Configures Spring Security for the application, typically the Config Server.
 * By disabling CSRF and HTTP Basic, it allows other services (especially the Eureka Server)
 * to access configuration endpoints without complex authentication headers.
 *
 * @since 0.0.1
 * @author yuanmz9432
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure
{
    /**
     * Defines the security filter chain for HTTP requests.
     *
     * @param httpSecurity The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain instance.
     * @throws Exception if configuration fails.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF (Cross-Site Request Forgery) protection
        // Disable HTTP Basic authentication
        return httpSecurity
            .httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .build();
    }
}
