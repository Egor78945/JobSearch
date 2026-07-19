package com.example.vacancy_manager_service.service.mapper;

import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyRequest;
import com.example.vacancy_manager_service.service.api.gigachat.ReactiveGigachatApiManager;
import com.example.vacancy_manager_service.service.api.token.ApiTokenHandler;
import com.example.vacancy_manager_service.service.util.TextResourceManger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tools.jackson.databind.json.JsonMapper;

@Service
public class GigachatTextMapperReactive implements ReactiveLLMTextMapper<HeadHunterVacancyRequest> {
    private final ReactiveGigachatApiManager reactiveGigachatApiManager;
    private final ApiTokenHandler apiTokenHandler;
    private final TextResourceManger resourceManger;
    private final JsonMapper objectMapper;

    public GigachatTextMapperReactive(ReactiveGigachatApiManager reactiveGigachatApiManager, @Qualifier("gigachatApiTokenHandler") ApiTokenHandler apiTokenHandler, TextResourceManger resourceManger, JsonMapper objectMapper) {
        this.reactiveGigachatApiManager = reactiveGigachatApiManager;
        this.apiTokenHandler = apiTokenHandler;
        this.resourceManger = resourceManger;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<HeadHunterVacancyRequest> mapFromString(String text) {
        String message = String.format(resourceManger.getAsString("static/prompt/gigachat_user_user_request_to_dto.txt"), text);

        return reactiveGigachatApiManager.textMessage(apiTokenHandler.getAccessToken(), message)
                .mapNotNull(r -> r.getChoices()[0].getMessage().getContent())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("empty response")))
                .map(json -> objectMapper.readValue(json, HeadHunterVacancyRequest.class));
    }
}
