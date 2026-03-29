package com.example.vacancy_manager_service.service;

import reactor.core.publisher.Mono;

public interface ReactiveVacancyService<V> extends VacancyService<Mono<V>>{
}
