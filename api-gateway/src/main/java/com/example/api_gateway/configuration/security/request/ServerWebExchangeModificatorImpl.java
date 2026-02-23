package com.example.api_gateway.configuration.security.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class ServerWebExchangeModificatorImpl implements ServerWebExchangeModificator<ServerWebExchange>{
    protected final HttpRequestModificator<ServerHttpRequest> httpRequestModificator;

    public ServerWebExchangeModificatorImpl(HttpRequestModificator<ServerHttpRequest> httpRequestModificator) {
        this.httpRequestModificator = httpRequestModificator;
    }

    @Override
    public ServerWebExchange modifyRequestHeaders(ServerWebExchange exchange, HttpHeaders headers) {
        return exchange.mutate()
                .request(httpRequestModificator.addHeaders(exchange.getRequest(), headers))
                .build();
    }
}
