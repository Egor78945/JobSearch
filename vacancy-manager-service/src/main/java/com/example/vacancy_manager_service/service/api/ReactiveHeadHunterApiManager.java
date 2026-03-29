package com.example.vacancy_manager_service.service.api;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.HeadHunterVacancyResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ReactiveHeadHunterApiManager {
    Mono<HeadHunterVacancyResponse> vacancySearch(String accessToken, Map<HeadHunterVacancyParameter, String> parameters);
    Mono<HeadHunterAuthorizationResponse> authorize(String clientId, String clientSecret);
}
