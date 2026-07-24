package com.example.vacancy_manager_service.service;

import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterRelevantVacancyRequest;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterRelevantVacancyResponse;
import reactor.core.publisher.Mono;

public interface ReactiveVacancyManagerService {
    Mono<HeadHunterRelevantVacancyResponse> defineRelevantVacancy(HeadHunterRelevantVacancyRequest request);
}
