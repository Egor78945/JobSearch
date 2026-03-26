package com.example.vacancy_manager_service.controller;

import java.util.Map;

public interface VacancyController<V> {
    V findByParameters(Map<String, String> parameters);
}
