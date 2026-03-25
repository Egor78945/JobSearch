package com.example.vacancy_manager_service.service.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface WebClientService {
    <C> ResponseEntity<C> exchange(RequestEntity<?> httpEntity, Class<C> clazz);
}
