package com.example.vacancy_manager_service.enumeration;

import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public enum HeadHunterVacancyParameter {
    TEXT, SALARY, CURRENCY, AREA, PAGE, PER_PAGE;

    HeadHunterVacancyParameter() {
    }

    public static Map<String, String> mapToString(Map<HeadHunterVacancyParameter, String> map) {
        Map<String, String> result = new HashMap<>();
        if(map == null) {
            return result;
        }
        for (Map.Entry<HeadHunterVacancyParameter, String> entry : map.entrySet()) {
            result.put(entry.getKey().name().toLowerCase(), entry.getValue());
        }
        return result;
    }

    public static Map<HeadHunterVacancyParameter, String> mapFromString(Map<String, String> map) {
        Map<HeadHunterVacancyParameter, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                result.put(HeadHunterVacancyParameter.valueOf(entry.getKey().toUpperCase()), entry.getValue());
            } catch (IllegalArgumentException unknownParameter) {
                throw new IllegalArgumentException("unknown parameter: " + entry.getKey());
            }
        }
        return result;
    }

    public static Map<String, String> mapFromVacancyRequest(HeadHunterVacancyRequest vacancyRequest) {
        Map<String, String> result = new HashMap<>();
        result.put(HeadHunterVacancyParameter.TEXT.name().toLowerCase(), vacancyRequest.getText());
        result.put(HeadHunterVacancyParameter.AREA.name().toLowerCase(), String.valueOf(vacancyRequest.getArea()));
        result.put(HeadHunterVacancyParameter.CURRENCY.name().toLowerCase(), vacancyRequest.getCurrency());
        result.put(HeadHunterVacancyParameter.SALARY.name().toLowerCase(), String.valueOf(vacancyRequest.getSalary()));
        result.put(HeadHunterVacancyParameter.PER_PAGE.name().toLowerCase(), String.valueOf(vacancyRequest.getPer_page()));
        return result;
    }
}
