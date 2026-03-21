package com.example.api_gateway.service.processor;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public interface ReactiveHttpRequestProcessor<R> {
    Mono<R> process(ServerWebExchange exchange, WebFilterChain chain);
}
