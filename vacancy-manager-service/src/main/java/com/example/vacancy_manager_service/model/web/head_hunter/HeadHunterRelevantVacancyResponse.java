package com.example.vacancy_manager_service.model.web.head_hunter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeadHunterRelevantVacancyResponse {
    private String vacancy_url;
    private String description;

    public HeadHunterRelevantVacancyResponse(String vacancy_url, String description) {
        this.vacancy_url = vacancy_url;
        this.description = description;
    }

    public HeadHunterRelevantVacancyResponse() {
    }

    public String getVacancy_url() {
        return vacancy_url;
    }

    public void setVacancy_url(String vacancy_url) {
        this.vacancy_url = vacancy_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HeadHunterRelevantVacancyResponse{" +
                "vacancy_url='" + vacancy_url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
