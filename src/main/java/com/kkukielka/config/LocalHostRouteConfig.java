package com.kkukielka.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalHostRouteConfig {

    // ? matches one character
    // * matches zero or more characters
    // ** matches zero or more directories in a path
    // more about AntPathMatcher:
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html
    @Bean
    public RouteLocator localhostRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "api/v1/beerUpc/*")
                    .uri("http://localhost:8080")
                    .id("beer-service"))
                .route(r -> r.path("/api/v1/customers/**")
                    .uri("http://localhost:8081")
                    .id("order-service"))
                .build();
    }
}
