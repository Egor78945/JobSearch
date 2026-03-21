package com.example.api_gateway.service.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import java.net.URI;

public abstract class HttpRequestBuilder {
    public <R> RequestEntity<R> build(URI uri, HttpHeaders headers, HttpMethod httpMethod) {
        return new RequestEntity<>(headers, httpMethod, uri);
    }
}
