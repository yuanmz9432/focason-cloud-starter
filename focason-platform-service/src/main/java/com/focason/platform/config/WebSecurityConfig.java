// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfig
 * <p>
 * Primary configuration class for Spring Security in a stateless API environment (e.g., Microservice).
 * It defines the security filter chain rules, disabling features specific to session-based
 * applications (like form login and CSRF) and integrates the custom
 * {@link FsSecurityContextRepository} for user context propagation (e.g., MDC logging).
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity // 启用 Spring Security 的 Web 安全功能
@AllArgsConstructor(onConstructor = @__({
    @Autowired, @Lazy
}))
public class WebSecurityConfig
{
    /**
     * Injected custom repository responsible for loading and saving the SecurityContext.
     * In this stateless setup, it's primarily used to populate MDC from request headers
     * (as configured in {@link FsSecurityContextRepository}).
     */
    private final FsSecurityContextRepository fsSecurityContextRepository;

    /**
     * Defines the security filter chain, configuring security settings for HTTP requests.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception if configuration errors occur.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            // 1. Context Repository Configuration: Integrate the custom stateless context repository
            // 用于将会话上下文（如认证信息）的存取替换为自定义的逻辑，在这里主要是用于从Header中读取信息并放入MDC。
            .securityContext(securityContext -> securityContext
                .securityContextRepository(fsSecurityContextRepository))

            // 2. Authorization Rules
            .authorizeHttpRequests(auth -> auth
                // 允许所有请求 ("/**") 访问。
                // 这通常意味着身份验证由前置组件（如API Gateway）完成，本服务信任并处理传入的Header。
                .anyRequest().permitAll())

            // 3. Disable Session-Based Authentication Mechanisms (Typical for REST APIs)
            .httpBasic(httpBasic -> httpBasic.disable()) // 关闭HTTP Basic认证
            .formLogin(formLogin -> formLogin.disable()) // 关闭表单登陆

            // 4. Disable CSRF protection
            // 由于是无状态API，不依赖Cookie/Session，所以通常禁用CSRF。
            .csrf(csrf -> csrf.disable()) // CSRF无效化
            .build(); // 构建 SecurityFilterChain
    }
}
