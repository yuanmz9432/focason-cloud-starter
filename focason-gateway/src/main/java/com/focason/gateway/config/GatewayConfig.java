// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.gateway.config;


import com.focason.gateway.filter.AuthenticationTokenFilter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * GatewayConfig
 * <p>
 * Primary configuration class for Spring Cloud Gateway, defining routing rules,
 * cross-origin resource sharing (CORS) policies, and foundational message converters.
 *
 * <p>
 * This configuration determines how external requests are handled, authenticated,
 * and forwarded to the appropriate downstream microservices via service discovery (Eureka).
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.cloud.gateway.route.RouteLocator
 */
@Configuration
@AllArgsConstructor(onConstructor = @__({
    @Autowired, @Lazy
}))
public class GatewayConfig
{
    private final AuthenticationTokenFilter authenticationTokenFilter;

    /**
     * Configures and defines the routing rules for the gateway.
     *
     * <p>
     * Routes are defined here to map specific URL paths to logical service IDs
     * resolved by the service discovery mechanism (Load Balancing, 'lb://').
     * The {@code authenticationTokenFilter} is applied to secure the routes.
     * </p>
     *
     * <ul>
     * <li>**focason-service-registry**: Routes requests to {@code /api/v1/eureka/**} and strips the first three path
     * segments.</li>
     * <li>**focason-platform-service**: Routes various core API paths (auth, users, files, notifications) and applies
     * the
     * authentication filter.</li>
     * </ul>
     *
     * @param builder {@link RouteLocatorBuilder} for fluent route definition.
     * @return {@link RouteLocator} containing the complete set of routing definitions.
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("focason-service-registry",
                r -> r.path("/api/v1/eureka/**").filters(f -> f.filter(authenticationTokenFilter).stripPrefix(3))
                    .uri("lb://focason-service-registry"))
            .route("focason-platform-service",
                r -> r.path("/api/v1/notifications/**", "/api/v1/auth/**", "/api/v1/users/**", "/api/v1/files/**")
                    .filters(f -> f.filter(authenticationTokenFilter))
                    .uri("lb://focason-platform-service"))
            .build();
    }

    /**
     * Configures the Cross-Origin Resource Sharing (CORS) Web Filter.
     *
     * <p>
     * This allows external clients (e.g., front-end applications) from specified
     * origins to access the gateway endpoints. Credentials are allowed, and all
     * common HTTP methods and headers are permitted for the entire application path.
     * </p>
     *
     * @return {@link CorsWebFilter} configured with allowed origins and methods.
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        // Specify allowed front-end origins
        corsConfig.setAllowedOrigins(
            List.of("http://192.168.3.5:3000", "http://localhost:3000", "https://focason.com"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    /**
     * Registers HTTP Message Converters for serialization and deserialization.
     *
     * <p>
     * This bean ensures that the Gateway can correctly convert between Java objects
     * and request/response body formats (like JSON/XML).
     * </p>
     *
     * @param converters {@link HttpMessageConverter} providers available in the context.
     * @return {@link HttpMessageConverters} containing all registered converters.
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        // Collects all available converters and wraps them into HttpMessageConverters
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
