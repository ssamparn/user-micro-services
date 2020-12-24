package com.microservices.userapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route.path("/users/**")
                        .uri("lb://user-management-micro-service"))
                .route(route -> route.path("/accounts/**")
                        .uri("lb://account-management-micro-service"))
                .build();
    }
}
