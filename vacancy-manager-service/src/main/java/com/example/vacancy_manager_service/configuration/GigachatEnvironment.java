package com.example.vacancy_manager_service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GigachatEnvironment {
    private final String GIGACHAT_API_MESSAGE;
    private final String GIGACHAT_API_ACCESS;

    public GigachatEnvironment(@Value("${api.gigachat.message.uri}") String GIGACHAT_API_MESSAGE, @Value("${api.gigachat.token.uri}") String GIGACHAT_API_ACCESS) {
        this.GIGACHAT_API_MESSAGE = GIGACHAT_API_MESSAGE;
        this.GIGACHAT_API_ACCESS = GIGACHAT_API_ACCESS;
    }

    public String getGigachatAuthorizationKey() {
        return System.getenv("GIGACHAT_AUTHORIZATION_KEY");
    }

    public String getGIGACHAT_API_MESSAGE() {
        return GIGACHAT_API_MESSAGE;
    }

    public String getGIGACHAT_API_ACCESS() {
        return GIGACHAT_API_ACCESS;
    }
}
