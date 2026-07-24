package com.example.vacancy_manager_service.service;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface ReactiveVacancySearchService<V> {
    Mono<V[]> searchVacancy(Map<String, String> parameters);

    Mono<V[]> searchVacancy(String text);
}
