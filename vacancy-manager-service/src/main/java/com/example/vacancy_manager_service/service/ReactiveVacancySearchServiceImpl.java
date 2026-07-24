package com.example.vacancy_manager_service.service;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterRelevantVacancyRequest;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyRequest;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.api.head_hunter.ReactiveHeadHunterApiManager;
import com.example.vacancy_manager_service.service.api.token.HeadHunterApiTokenHandler;
import com.example.vacancy_manager_service.service.mapper.ReactiveLLMTextMapper;
import com.example.vacancy_manager_service.service.util.TextResourceManger;
import com.example.vacancy_manager_service.service.util.UriUtilities;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class ReactiveVacancySearchServiceImpl implements ReactiveVacancySearchService<HeadHunterVacancyResponse.Item> {
    private final ReactiveHeadHunterApiManager headHunterApiManager;
    private final HeadHunterApiTokenHandler headHunterApiTokenHandler;
    private final ReactiveLLMTextMapper reactiveLLMTextMapper;
    private final TextResourceManger resourceManager;

    public ReactiveVacancySearchServiceImpl(ReactiveHeadHunterApiManager headHunterApiManager, HeadHunterApiTokenHandler headHunterApiTokenHandler, ReactiveLLMTextMapper reactiveLLMTextMapper, TextResourceManger resourceManager) {
        this.headHunterApiManager = headHunterApiManager;
        this.headHunterApiTokenHandler = headHunterApiTokenHandler;
        this.reactiveLLMTextMapper = reactiveLLMTextMapper;
        this.resourceManager = resourceManager;
    }

    @Override
    public Mono<HeadHunterVacancyResponse.Item[]> searchVacancy(Map<String, String> parameters) {
        return headHunterApiManager.vacancySearch(headHunterApiTokenHandler.getAccessToken(), parameters)
                .map(HeadHunterVacancyResponse::getItems);
    }

    @Override
    public Mono<HeadHunterVacancyResponse.Item[]> searchVacancy(String text) {
        return reactiveLLMTextMapper.mapFromString(String.format(resourceManager.getAsString("static/prompt/gigachat_user_user_request_to_dto.txt"), text), HeadHunterVacancyRequest.class)
                .flatMap(r -> searchVacancy(UriUtilities.encodeParams(HeadHunterVacancyParameter.mapFromVacancyRequest(r), StandardCharsets.UTF_8)));
    }
}
