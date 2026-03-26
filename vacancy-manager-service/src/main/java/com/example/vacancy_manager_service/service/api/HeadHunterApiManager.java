package com.example.vacancy_manager_service.service.api;

import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.HeadHunterVacancyResponse;

import java.util.Map;

public interface HeadHunterApiManager {
    HeadHunterVacancyResponse vacancySearch(String accessToken, Map<HeadHunterVacancyParameter, String> parameters);
    HeadHunterAuthorizationResponse authorize(String clientId, String clientSecret);
}
