package com.example.vacancy_manager_service.service.api.gigachat;

import com.example.vacancy_manager_service.model.web.gigachat.GigachatAuthorizationResponse;
import com.example.vacancy_manager_service.model.web.gigachat.GigachatResponse;

public interface GigachatApiManager {
    GigachatResponse textMessage(String accessToken, String message);
    GigachatAuthorizationResponse getAccessToken(String authorizationKey);
}
