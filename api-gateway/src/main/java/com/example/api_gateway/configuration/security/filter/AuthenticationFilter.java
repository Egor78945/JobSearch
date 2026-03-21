package com.example.api_gateway.configuration.security.filter;

import com.example.api_gateway.configuration.environment.SecurityEnvironment;
import com.example.api_gateway.service.processor.ReactiveHttpRequestProcessor;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class AuthenticationFilter implements WebFilter, CustomWebFilter {
    private final ReactiveHttpRequestProcessor<Void> requestProcessor;
    private final SecurityEnvironment securityEnvironment;

    public AuthenticationFilter(ReactiveHttpRequestProcessor<Void> requestProcessor, SecurityEnvironment securityEnvironment) {
        this.requestProcessor = requestProcessor;
        this.securityEnvironment = securityEnvironment;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (securityEnvironment.isPublicPath(exchange.getRequest().getURI().getPath())) {
            return chain.filter(exchange);
        }
        return requestProcessor.process(exchange, chain);
    }
}
