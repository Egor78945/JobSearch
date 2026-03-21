package com.example.api_gateway.configuration.security.filter;

import com.example.api_gateway.configuration.environment.SecurityEnvironment;
import com.example.api_gateway.service.processor.ReactiveHttpRequestProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFilterConfiguration {
    @Bean
    public AuthenticationFilter jwtFilter(ReactiveHttpRequestProcessor<Void> requestProcessor, SecurityEnvironment securityEnvironment) {
        return new AuthenticationFilter(requestProcessor, securityEnvironment);
    }
}
