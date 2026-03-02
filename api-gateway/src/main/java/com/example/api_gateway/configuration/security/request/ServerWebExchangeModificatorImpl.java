package com.example.api_gateway.configuration.security.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class ServerWebExchangeModificatorImpl implements ServerWebExchangeModificator<ServerWebExchange> {
    protected final HttpRequestModificator<ServerHttpRequest> httpRequestModificator;
    protected final HttpResponseModificator<ServerHttpResponse> httpResponseModificator;

    public ServerWebExchangeModificatorImpl(HttpRequestModificator<ServerHttpRequest> httpRequestModificator, HttpResponseModificator<ServerHttpResponse> httpResponseModificator) {
        this.httpRequestModificator = httpRequestModificator;
        this.httpResponseModificator = httpResponseModificator;
    }

    @Override
    public ServerWebExchange modifyRequestHeaders(ServerWebExchange exchange, HttpHeaders headers) {
        return exchange.mutate()
                .request(httpRequestModificator.addHeaders(exchange.getRequest(), headers))
                .build();
    }

    @Override
    public ServerWebExchange modifyResponseCookie(ServerWebExchange exchange, ResponseCookie cookie) {
        return exchange.mutate()
                .response(httpResponseModificator.addCookie(exchange.getResponse(), cookie))
                .build();
    }
}
