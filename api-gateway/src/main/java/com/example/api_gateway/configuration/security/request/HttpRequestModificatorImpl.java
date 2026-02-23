package com.example.api_gateway.configuration.security.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestModificatorImpl implements HttpRequestModificator<ServerHttpRequest> {
    @Override
    public ServerHttpRequest addHeaders(ServerHttpRequest request, HttpHeaders headers) {
        return request.mutate()
                .headers(h -> h.addAll(headers))
                .build();
    }
}
