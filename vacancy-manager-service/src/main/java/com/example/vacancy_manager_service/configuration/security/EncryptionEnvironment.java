package com.example.vacancy_manager_service.configuration.security;

import org.springframework.stereotype.Component;

@Component
public class EncryptionEnvironment {
    public String getSecret() {
        return System.getenv("ENCRYPTION_SECRET");
    }
}
