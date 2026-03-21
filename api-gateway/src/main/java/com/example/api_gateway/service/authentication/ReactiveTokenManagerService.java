package com.example.api_gateway.service.authentication;

import reactor.core.publisher.Mono;

public interface ReactiveTokenManagerService<R> {
    Mono<R> refreshToken(String refreshToken);
}
