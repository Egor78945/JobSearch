package com.example.api_gateway.configuration.security.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

public interface ServerWebExchangeModificator<E extends ServerWebExchange> {
    E modifyRequestHeaders(E exchange, HttpHeaders request);
}
