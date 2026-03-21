package com.example.api_gateway.service.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ReactiveWebClientService {
    <C> Mono<ResponseEntity<C>> exchange(RequestEntity<?> httpEntity, Class<C> clazz);
}
