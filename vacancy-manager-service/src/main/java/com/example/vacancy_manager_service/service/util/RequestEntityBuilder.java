package com.example.vacancy_manager_service.service.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import java.util.Map;

public interface RequestEntityBuilder {
    <R> RequestEntity<R> build(R body, HttpMethod httpMethod, HttpHeaders headers, String uri);

    <R> RequestEntity<R> build(R body, HttpMethod httpMethod, HttpHeaders headers, Map<String, String> uriParameters, String uri);

    RequestEntity<Void> build(HttpMethod httpMethod, HttpHeaders headers, Map<String, String> uriParameters, String uri);
}
