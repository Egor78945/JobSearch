package com.example.vacancy_manager_service.service.api.head_hunter;

import com.example.vacancy_manager_service.configuration.HeadHunterEnvironment;
import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.util.RequestEntityBuilder;
import com.example.vacancy_manager_service.service.web.WebClientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Component
public class HeadHunterApiManagerImpl implements HeadHunterApiManager {
    protected final WebClientService webClientService;
    protected final RequestEntityBuilder requestEntityBuilder;
    protected final HeadHunterEnvironment headHunterEnvironment;

    public HeadHunterApiManagerImpl(WebClientService webClientService, RequestEntityBuilder requestEntityBuilder, HeadHunterEnvironment headHunterEnvironment) {
        this.webClientService = webClientService;
        this.requestEntityBuilder = requestEntityBuilder;
        this.headHunterEnvironment = headHunterEnvironment;
    }

    @Override
    public HeadHunterVacancyResponse vacancySearch(String accessToken, Map<HeadHunterVacancyParameter, String> parameters) {
        HttpHeaders headers = new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", accessToken))));
        RequestEntity<Void> request = requestEntityBuilder.build(HttpMethod.GET, headers, HeadHunterVacancyParameter.mapToString(parameters), headHunterEnvironment.getHEAD_HUNTER_API_VACANCIES());
        return webClientService.exchange(request, HeadHunterVacancyResponse.class).getBody();
    }

    @Override
    public HeadHunterAuthorizationResponse authorize(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)));
        RequestEntity<MultiValueMap<String, String>> request = requestEntityBuilder.build(MultiValueMap.fromSingleValue(Map.of("grant_type", "client_credentials", "client_id", clientId, "client_secret", clientSecret)), HttpMethod.POST, headers, headHunterEnvironment.getHEAD_HUNTER_API_TOKEN());
        return webClientService.exchange(request, HeadHunterAuthorizationResponse.class).getBody();
    }
}
