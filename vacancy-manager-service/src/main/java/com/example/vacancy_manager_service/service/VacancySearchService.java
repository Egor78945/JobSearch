package com.example.vacancy_manager_service.service;

import java.util.Map;

public interface VacancySearchService<V> {
    V[] searchVacancy(Map<String, String> parameters);

    V[] searchVacancy(String text);
}
