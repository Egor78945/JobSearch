package com.example.api_gateway.service.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface WebClientService {
    <C> ResponseEntity<C> exchange(RequestEntity<?> httpEntity, Class<C> clazz);
}
