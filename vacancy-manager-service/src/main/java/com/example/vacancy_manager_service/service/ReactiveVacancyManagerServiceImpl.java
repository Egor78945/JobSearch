package com.example.vacancy_manager_service.service;

import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterRelevantVacancyRequest;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterRelevantVacancyResponse;
import com.example.vacancy_manager_service.service.api.gigachat.ReactiveGigachatApiManager;
import com.example.vacancy_manager_service.service.api.token.GigachatApiTokenHandler;
import com.example.vacancy_manager_service.service.mapper.ReactiveLLMTextMapper;
import com.example.vacancy_manager_service.service.util.TextResourceManger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tools.jackson.databind.json.JsonMapper;

@Service
public class ReactiveVacancyManagerServiceImpl implements ReactiveVacancyManagerService {
    private final ReactiveLLMTextMapper reactiveLLMTextMapper;
    private final TextResourceManger textResourceManger;
    private final JsonMapper jsonMapper;

    public ReactiveVacancyManagerServiceImpl(ReactiveLLMTextMapper reactiveLLMTextMapper, TextResourceManger textResourceManger, JsonMapper jsonMapper) {
        this.reactiveLLMTextMapper = reactiveLLMTextMapper;
        this.textResourceManger = textResourceManger;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Mono<HeadHunterRelevantVacancyResponse> defineRelevantVacancy(HeadHunterRelevantVacancyRequest request) {
        return reactiveLLMTextMapper.mapFromString(String.format(textResourceManger.getAsString("static/prompt/gigachat_user_response_prompt.txt"), jsonMapper.writeValueAsString(request)), HeadHunterRelevantVacancyResponse.class);
    }
}
