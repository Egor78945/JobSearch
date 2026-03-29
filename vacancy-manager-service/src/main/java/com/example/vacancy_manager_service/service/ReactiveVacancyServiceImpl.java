package com.example.vacancy_manager_service.service;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.api.ReactiveHeadHunterApiManager;
import com.example.vacancy_manager_service.service.api.token.HeadHunterApiTokenHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ReactiveVacancyServiceImpl implements ReactiveVacancyService<HeadHunterVacancyResponse> {
    private final ReactiveHeadHunterApiManager headHunterApiManager;
    private final HeadHunterApiTokenHandler headHunterApiTokenHandler;

    public ReactiveVacancyServiceImpl(ReactiveHeadHunterApiManager headHunterApiManager, HeadHunterApiTokenHandler headHunterApiTokenHandler) {
        this.headHunterApiManager = headHunterApiManager;
        this.headHunterApiTokenHandler = headHunterApiTokenHandler;
    }

    @Override
    public Mono<HeadHunterVacancyResponse> searchVacancy(Map<String, String> parameters) {
        return headHunterApiManager.vacancySearch(headHunterApiTokenHandler.getAccessToken(), HeadHunterVacancyParameter.mapFromString(parameters));
    }
}
