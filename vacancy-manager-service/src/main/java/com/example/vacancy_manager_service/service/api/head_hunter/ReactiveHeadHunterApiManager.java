package com.example.vacancy_manager_service.service.api.head_hunter;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ReactiveHeadHunterApiManager {
    Mono<HeadHunterVacancyResponse> vacancySearch(String accessToken, Map<String, String> parameters);
    Mono<HeadHunterAuthorizationResponse> authorize(String clientId, String clientSecret);
}
