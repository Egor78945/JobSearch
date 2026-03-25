package com.example.vacancy_manager_service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadHunterEnvironment {
    private final String HEAD_HUNTER_API_VACANCIES;
    private final String HEAD_HUNTER_API_TOKEN;

    public HeadHunterEnvironment(@Value("${api.hh.vacancies.uri}") String HEAD_HUNTER_API_VACANCIES, @Value("${api.hh.token.uri}") String headHunterApiToken) {
        this.HEAD_HUNTER_API_VACANCIES = HEAD_HUNTER_API_VACANCIES;
        HEAD_HUNTER_API_TOKEN = headHunterApiToken;
    }

    public String getHEAD_HUNTER_API_VACANCIES() {
        return HEAD_HUNTER_API_VACANCIES;
    }

    public String getHEAD_HUNTER_API_TOKEN() {
        return HEAD_HUNTER_API_TOKEN;
    }

    public String getHEAD_HUNTER_CLIENT_ID() {
        return System.getenv("HH_CLIENT_ID");
    }

    public String getHEAD_HUNTER_CLIENT_SECRET() {
        return System.getenv("HH_CLIENT_SECRET");
    }
}
