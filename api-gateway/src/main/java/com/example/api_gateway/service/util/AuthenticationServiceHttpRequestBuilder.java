package com.example.api_gateway.service.util;

import com.example.api_gateway.configuration.environment.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class AuthenticationServiceHttpRequestBuilder extends HttpRequestBuilder {
    private final WebEnvironment webEnvironment;

    public AuthenticationServiceHttpRequestBuilder(WebEnvironment webEnvironment) {
        this.webEnvironment = webEnvironment;
    }

    public <R> RequestEntity<R> refreshTokenRequest(HttpHeaders headers) {
        return build(URI.create(webEnvironment.getAPI_AUTHENTICATION_SERVICE_TOKEN_REFRESH()), headers, HttpMethod.GET);
    }
}
