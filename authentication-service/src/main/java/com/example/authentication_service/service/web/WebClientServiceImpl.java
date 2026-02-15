package com.example.authentication_service.service.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebClientServiceImpl implements WebClientService {
    protected final RestTemplate restTemplate;

    public WebClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <C> ResponseEntity<C> post(String url, HttpEntity<?> httpEntity, Class<C> clazz) {
        return restTemplate.postForEntity(url, httpEntity, clazz);
    }
}