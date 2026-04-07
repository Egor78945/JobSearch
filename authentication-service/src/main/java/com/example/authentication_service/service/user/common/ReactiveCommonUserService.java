package com.example.authentication_service.service.user.common;

import reactor.core.publisher.Mono;

public interface ReactiveCommonUserService {
    Mono<Void> deleteByEmail(String email);
}
