package com.example.api_gateway.configuration.security.filter;

import com.example.api_gateway.service.processor.RefreshTokenResponseCustomizerProcessor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RefreshTokenCustomizerFilter implements GlobalFilter, Ordered {
    private final RefreshTokenResponseCustomizerProcessor responseProcessor;

    public RefreshTokenCustomizerFilter(RefreshTokenResponseCustomizerProcessor responseProcessor) {
        this.responseProcessor = responseProcessor;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .doOnSuccess(aVoid -> {
                    if (isProcessable(exchange)) {
                        responseProcessor.process(exchange.getResponse());
                    }
                })
                .onErrorResume(error -> Mono.empty());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

    private boolean isProcessable(ServerWebExchange exchange) {
        return exchange.getResponse().getStatusCode() != null && exchange.getResponse().getStatusCode().is2xxSuccessful();
    }
}
