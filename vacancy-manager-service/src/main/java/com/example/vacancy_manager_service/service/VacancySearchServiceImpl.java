package com.example.vacancy_manager_service.service;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.api.head_hunter.HeadHunterApiManager;
import com.example.vacancy_manager_service.service.api.token.HeadHunterApiTokenHandler;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VacancySearchServiceImpl implements VacancySearchService<HeadHunterVacancyResponse.Item> {
    private final HeadHunterApiManager headHunterApiManager;
    private final HeadHunterApiTokenHandler headHunterApiTokenHandler;

    public VacancySearchServiceImpl(HeadHunterApiManager headHunterApiManager, HeadHunterApiTokenHandler headHunterApiTokenHandler) {
        this.headHunterApiManager = headHunterApiManager;
        this.headHunterApiTokenHandler = headHunterApiTokenHandler;
    }

    @Override
    public HeadHunterVacancyResponse.Item[] searchVacancy(Map<String, String> parameters) {
        return headHunterApiManager.vacancySearch(headHunterApiTokenHandler.getAccessToken(), HeadHunterVacancyParameter.mapFromString(parameters)).getItems();
    }

    @Override
    public HeadHunterVacancyResponse.Item[] searchVacancy(String text) {
        return null;
    }
}
