package com.example.vacancy_manager_service.controller;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface ReactiveVacancyController<V> {
    Mono<V[]> findByParameters(Map<String, String> parameters);
    Mono<V[]> findByText(String text);
}
