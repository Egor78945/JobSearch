package com.example.vacancy_manager_service.service.util;

import jakarta.ws.rs.core.UriBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;

@Component
public class RequestEntityBuilderImpl implements RequestEntityBuilder {
    @Override
    public <R> RequestEntity<R> build(R body, HttpMethod httpMethod, HttpHeaders headers, String uri) {
        URI uriModel = URI.create(uri);
        return new RequestEntity<>(body, headers, httpMethod, uriModel);
    }

    @Override
    public <R> RequestEntity<R> build(R body, HttpMethod httpMethod, HttpHeaders headers, Map<String, String> uriParameters, String uri) {
        URI uriModel = UriUtilities.buildFrom(uri, uriParameters);
        return new RequestEntity<>(body, headers, httpMethod, uriModel);
    }

    @Override
    public RequestEntity<Void> build(HttpMethod httpMethod, HttpHeaders headers, Map<String, String> uriParameters, String uri) {
        URI uriModel = UriUtilities.buildFrom(uri, uriParameters);
        return new RequestEntity<>(headers, httpMethod, uriModel);
    }
}
