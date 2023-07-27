package com.example.siteservice.functional.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.siteservice.functional.controller.SiteController;

@Configuration(proxyBeanMethods = false)
public class SiteRouter {
    @Bean
    RouterFunction<ServerResponse> route(SiteController handler) {
        return RouterFunctions
            .route(GET("/sites/{id}"), handler::view);
    }
}
