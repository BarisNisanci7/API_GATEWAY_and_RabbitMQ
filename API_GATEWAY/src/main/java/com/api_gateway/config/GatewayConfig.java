package com.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users/**")
                        .uri("http://localhost:8081"))
                .route("flight-service", r -> r.path("/flights/**")
                        .uri("http://localhost:8082"))
                .route("ticket-service", r -> r.path("/tickets/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}