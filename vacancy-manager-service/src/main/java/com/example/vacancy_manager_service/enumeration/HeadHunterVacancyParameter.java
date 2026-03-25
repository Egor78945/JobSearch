package com.example.vacancy_manager_service.enumeration;

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
}
