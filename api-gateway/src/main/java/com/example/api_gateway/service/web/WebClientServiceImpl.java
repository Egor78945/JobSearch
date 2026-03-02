package com.example.api_gateway.service.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebClientServiceImpl implements WebClientService {
    protected final RestTemplate restTemplate;

    public WebClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <C> ResponseEntity<C> exchange(RequestEntity<?> httpEntity, Class<C> clazz) {
        return restTemplate.exchange(httpEntity, clazz);
    }
}