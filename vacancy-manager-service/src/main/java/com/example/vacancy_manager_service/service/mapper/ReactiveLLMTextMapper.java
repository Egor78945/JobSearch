package com.example.vacancy_manager_service.service.mapper;

import reactor.core.publisher.Mono;

public interface ReactiveLLMTextMapper<T> {
    Mono<T> mapFromString(String text);
}
