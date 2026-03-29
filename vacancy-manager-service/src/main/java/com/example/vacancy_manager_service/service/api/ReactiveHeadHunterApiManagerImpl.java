package com.example.vacancy_manager_service.service.api;

import com.example.vacancy_manager_service.configuration.HeadHunterEnvironment;
import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.util.RequestEntityBuilder;
import com.example.vacancy_manager_service.service.web.ReactiveWebClientService;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ReactiveHeadHunterApiManagerImpl implements ReactiveHeadHunterApiManager {
    protected final ReactiveWebClientService reactiveWebClientService;
    protected final RequestEntityBuilder requestEntityBuilder;
    protected final HeadHunterEnvironment headHunterEnvironment;

    public ReactiveHeadHunterApiManagerImpl(ReactiveWebClientService reactiveWebClientService, RequestEntityBuilder requestEntityBuilder, HeadHunterEnvironment headHunterEnvironment) {
        this.reactiveWebClientService = reactiveWebClientService;
        this.requestEntityBuilder = requestEntityBuilder;
        this.headHunterEnvironment = headHunterEnvironment;
    }

    @Override
    public Mono<HeadHunterVacancyResponse> vacancySearch(String accessToken, Map<HeadHunterVacancyParameter, String> parameters) {
        HttpHeaders headers = new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", accessToken))));
        RequestEntity<Void> request = requestEntityBuilder.build(HttpMethod.GET, headers, HeadHunterVacancyParameter.mapToString(parameters), headHunterEnvironment.getHEAD_HUNTER_API_VACANCIES());
        return reactiveWebClientService.exchange(request, HeadHunterVacancyResponse.class)
                .mapNotNull(HttpEntity::getBody)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("empty response")));
    }

    @Override
    public Mono<HeadHunterAuthorizationResponse> authorize(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)));
        RequestEntity<MultiValueMap<String, String>> request = requestEntityBuilder.build(MultiValueMap.fromSingleValue(Map.of("grant_type", "client_credentials", "client_id", clientId, "client_secret", clientSecret)), HttpMethod.POST, headers, headHunterEnvironment.getHEAD_HUNTER_API_TOKEN());
        return reactiveWebClientService.exchange(request, HeadHunterAuthorizationResponse.class)
                .mapNotNull(HttpEntity::getBody)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("empty response")));
    }
}
