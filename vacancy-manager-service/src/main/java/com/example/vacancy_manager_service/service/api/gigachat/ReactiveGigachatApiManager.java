package com.example.vacancy_manager_service.service.api.gigachat;

import com.example.vacancy_manager_service.model.web.gigachat.GigachatResponse;
import com.example.vacancy_manager_service.model.web.gigachat.GigachatAuthorizationResponse;
import reactor.core.publisher.Mono;

public interface ReactiveGigachatApiManager {
    Mono<GigachatResponse> textMessage(String accessToken, String message);
    Mono<GigachatAuthorizationResponse> getAccessToken(String authorizationKey);
}
