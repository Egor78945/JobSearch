package com.example.vacancy_manager_service.model.web.head_hunter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeadHunterRelevantVacancyRequest {
    private String message;
    private HeadHunterVacancyResponse.Item[] vacancies;

    public HeadHunterRelevantVacancyRequest(String message, HeadHunterVacancyResponse.Item[] vacancies) {
        this.message = message;
        this.vacancies = vacancies;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HeadHunterVacancyResponse.Item[] getVacancies() {
        return vacancies;
    }

    public void setVacancies(HeadHunterVacancyResponse.Item[] vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public String toString() {
        return "HeadHunterRelevantVacancyRequest{" +
                "text='" + message + '\'' +
                ", vacancies=" + Arrays.toString(vacancies) +
                '}';
    }
}
