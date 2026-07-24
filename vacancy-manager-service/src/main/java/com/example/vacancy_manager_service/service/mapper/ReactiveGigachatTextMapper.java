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
public class ReactiveGigachatTextMapper implements ReactiveLLMTextMapper {
    private final ReactiveGigachatApiManager reactiveGigachatApiManager;
    private final ApiTokenHandler apiTokenHandler;
    private final JsonMapper objectMapper;

    public ReactiveGigachatTextMapper(ReactiveGigachatApiManager reactiveGigachatApiManager, @Qualifier("gigachatApiTokenHandler") ApiTokenHandler apiTokenHandler, JsonMapper objectMapper) {
        this.reactiveGigachatApiManager = reactiveGigachatApiManager;
        this.apiTokenHandler = apiTokenHandler;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> Mono<T> mapFromString(String text, Class<T> type) {

        return reactiveGigachatApiManager.textMessage(apiTokenHandler.getAccessToken(), text)
                .mapNotNull(r -> r.getChoices()[0].getMessage().getContent())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("empty response")))
                .map(json -> objectMapper.readValue(json, type));
    }
}
