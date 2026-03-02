package com.example.api_gateway.configuration.security.request;

import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class HttpResponseModificatorImpl implements HttpResponseModificator<ServerHttpResponse>{
    @Override
    public ServerHttpResponse addCookie(ServerHttpResponse response, ResponseCookie cookie) {
        response.addCookie(cookie);
        return response;
    }
}
