package com.example.authentication_service.service.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface WebClientService {
    <C> ResponseEntity<C> post(String url, HttpEntity<?> httpEntity, Class<C> clazz);
}
