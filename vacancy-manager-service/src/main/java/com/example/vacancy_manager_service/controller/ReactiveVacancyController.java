package com.example.vacancy_manager_service.controller;

import reactor.core.publisher.Mono;

public interface ReactiveVacancyController<V> extends VacancyController<Mono<V>>{
}
