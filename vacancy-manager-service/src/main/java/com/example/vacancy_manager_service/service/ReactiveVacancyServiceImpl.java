package com.example.vacancy_manager_service.service;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyRequest;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.api.head_hunter.ReactiveHeadHunterApiManager;
import com.example.vacancy_manager_service.service.api.token.HeadHunterApiTokenHandler;
import com.example.vacancy_manager_service.service.mapper.ReactiveLLMTextMapper;
import com.example.vacancy_manager_service.service.util.UriUtilities;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class ReactiveVacancyServiceImpl implements ReactiveVacancyService<HeadHunterVacancyResponse> {
    private final ReactiveHeadHunterApiManager headHunterApiManager;
    private final HeadHunterApiTokenHandler headHunterApiTokenHandler;
    private final ReactiveLLMTextMapper<HeadHunterVacancyRequest> reactiveLLMTextMapper;

    public ReactiveVacancyServiceImpl(ReactiveHeadHunterApiManager headHunterApiManager, HeadHunterApiTokenHandler headHunterApiTokenHandler, ReactiveLLMTextMapper<HeadHunterVacancyRequest> reactiveLLMTextMapper) {
        this.headHunterApiManager = headHunterApiManager;
        this.headHunterApiTokenHandler = headHunterApiTokenHandler;
        this.reactiveLLMTextMapper = reactiveLLMTextMapper;
    }

    @Override
    public Mono<HeadHunterVacancyResponse> searchVacancy(Map<String, String> parameters) {
        return headHunterApiManager.vacancySearch(headHunterApiTokenHandler.getAccessToken(), parameters);
    }

    @Override
    public Mono<HeadHunterVacancyResponse> searchVacancy(String text) {
        return reactiveLLMTextMapper.mapFromString(text)
                .flatMap(r -> searchVacancy(UriUtilities.encodeParams(HeadHunterVacancyParameter.mapFromVacancyRequest(r), StandardCharsets.UTF_8)));
    }
}
