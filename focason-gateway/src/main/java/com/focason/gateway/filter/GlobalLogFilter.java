// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GlobalLogFilter
 * <p>
 * A global filter implementation for the Spring Cloud Gateway responsible for logging
 * essential information about the request and response lifecycle, including
 * IP address, method, path, HTTP status code, and total processing duration.
 *
 * <p>
 * This filter operates across all routed requests, providing critical
 * observability into the gateway's operation.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.cloud.gateway.filter.GlobalFilter
 */
@Component
public class GlobalLogFilter implements GlobalFilter
{

    final Logger logger = LoggerFactory.getLogger(GlobalLogFilter.class);
    private static final String REQUEST_TIME_BEGIN = "Request Time Begin"; // Records the request start time
    private static final String REQUEST_INFO = "Request Info"; // Stores request log information

    /**
     * Executes the global logging filter logic.
     *
     * <p>
     * This method performs two main actions:
     * </p>
     * <ol>
     * <li>**Pre-Filter (Before Chain):** Records the request start time and basic request information (IP, Method,
     * Path) in the exchange attributes.</li>
     * <li>**Post-Filter (On Success):** After the request chain completes, it calculates the elapsed time, retrieves
     * the response status, and logs the complete transaction details.</li>
     * </ol>
     *
     * @param exchange The current server exchange.
     * @param chain The gateway filter chain to proceed with.
     * @return {@code Mono<Void>} signaling the completion of the filter execution.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 請求IPアドレス (Request IP Address)
        String ip = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = exchange.getRequest().getRemoteAddress() != null
                ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                : "Unknown";
        }

        // 請求開始時間 (Request start time)
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());

        // 請求基本情報 (Basic request information)
        String requestInfo = String.format("IP: %s | %s | %s",
            ip, request.getMethodValue(), request.getURI().getPath());
        exchange.getAttributes().put(REQUEST_INFO, requestInfo);

        // レスポンス (Response processing)
        return chain.filter(exchange).doOnSuccess((done) -> {
            ServerHttpResponse response = exchange.getResponse();
            Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
            long duration = (startTime != null) ? (System.currentTimeMillis() - startTime) : 0;

            logger.info("{} | Response: {} | 経過時間: {} ms",
                exchange.getAttribute(REQUEST_INFO),
                response.getStatusCode(),
                duration);
        });
    }
}
