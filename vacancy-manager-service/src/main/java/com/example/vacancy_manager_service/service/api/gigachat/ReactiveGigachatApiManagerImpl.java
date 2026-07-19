package com.example.vacancy_manager_service.service.api.gigachat;

import com.example.vacancy_manager_service.configuration.GigachatEnvironment;
import com.example.vacancy_manager_service.model.web.gigachat.GigachatAuthorizationResponse;
import com.example.vacancy_manager_service.model.web.gigachat.GigachatRequest;
import com.example.vacancy_manager_service.model.web.gigachat.GigachatResponse;
import com.example.vacancy_manager_service.service.web.ReactiveWebClientService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReactiveGigachatApiManagerImpl implements ReactiveGigachatApiManager {
    private final GigachatEnvironment gigachatEnvironment;
    private final ReactiveWebClientService reactiveWebClientService;

    public ReactiveGigachatApiManagerImpl(GigachatEnvironment gigachatEnvironment, ReactiveWebClientService reactiveWebClientService) {
        this.gigachatEnvironment = gigachatEnvironment;
        this.reactiveWebClientService = reactiveWebClientService;
    }

    @Override
    public Mono<GigachatResponse> textMessage(String accessToken, String message) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-Client-ID", UUID.randomUUID().toString());
        headerMap.put("X-Request-ID", UUID.randomUUID().toString());
        headerMap.put("X-Session-ID", UUID.randomUUID().toString());
        headerMap.put("User-Agent", UUID.randomUUID().toString());
        headerMap.put("Authorization", String.format("Bearer %s", accessToken));

        GigachatRequest gigachatRequest = new GigachatRequest("GigaChat", new GigachatRequest.Message[]{new GigachatRequest.Message("system", message)});

        HttpHeaders headers = new HttpHeaders(MultiValueMap.fromSingleValue(headerMap));
        RequestEntity<GigachatRequest> request = new RequestEntity<>(gigachatRequest, headers, HttpMethod.POST, URI.create(gigachatEnvironment.getGIGACHAT_API_MESSAGE()));

        return reactiveWebClientService.exchange(request, GigachatResponse.class)
                .mapNotNull(HttpEntity::getBody)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("empty response")));
    }

    @Override
    public Mono<GigachatAuthorizationResponse> getAccessToken(String authorizationKey) {
        HttpHeaders headers = new HttpHeaders(MultiValueMap.fromSingleValue(Map.of("RqUID", UUID.randomUUID().toString(), "Authorization", String.format("Basic %s", gigachatEnvironment.getGigachatAuthorizationKey()))));
        RequestEntity<MultiValueMap<String, String>> request = new RequestEntity<>(MultiValueMap.fromSingleValue(Map.of("scope", "GIGACHAT_API_PERS")), headers, HttpMethod.POST, URI.create(gigachatEnvironment.getGIGACHAT_API_ACCESS()));
        return reactiveWebClientService.exchange(request, GigachatAuthorizationResponse.class)
                .mapNotNull(HttpEntity::getBody)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("empty response")));
    }
}
