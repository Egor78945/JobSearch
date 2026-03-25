package com.example.vacancy_manager_service.service;

import java.util.Map;

public interface VacancyService<V> {
    V findByParameters(Map<String, String> parameters);
}
