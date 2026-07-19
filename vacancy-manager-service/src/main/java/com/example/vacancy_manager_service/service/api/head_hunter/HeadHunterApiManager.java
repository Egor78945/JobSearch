package com.example.vacancy_manager_service.service.api.head_hunter;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyResponse;

import java.util.Map;

public interface HeadHunterApiManager {
    HeadHunterVacancyResponse vacancySearch(String accessToken, Map<HeadHunterVacancyParameter, String> parameters);
    HeadHunterAuthorizationResponse authorize(String clientId, String clientSecret);
}
