package com.example.api_gateway.configuration.security;

import com.example.api_gateway.configuration.security.filter.CustomWebFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

@Configuration
public class HttpHandlerConfiguration {
    @Bean
    public HttpHandler httpHandler(ApplicationContext applicationContext) {
        return WebHttpHandlerBuilder
                .applicationContext(applicationContext)
                .filters(f -> f.removeIf(wf -> CustomWebFilter.class.isAssignableFrom(wf.getClass())))
                .build();
    }
}
