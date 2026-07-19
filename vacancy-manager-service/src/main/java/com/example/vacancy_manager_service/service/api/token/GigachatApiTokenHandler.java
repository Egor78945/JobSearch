package com.example.vacancy_manager_service.service.api.token;

import com.example.vacancy_manager_service.configuration.GigachatEnvironment;
import com.example.vacancy_manager_service.service.api.gigachat.GigachatApiManager;
import com.example.vacancy_manager_service.service.security.EncryptionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GigachatApiTokenHandler implements ApiTokenHandler {
    private final GigachatApiManager gigachatApiManager;
    private final GigachatEnvironment gigachatEnvironment;
    private final EncryptionService<String> encryptionService;
    private volatile String accessToken;

    public GigachatApiTokenHandler(GigachatApiManager gigachatApiManager, GigachatEnvironment gigachatEnvironment, EncryptionService<String> encryptionService) {
        this.gigachatApiManager = gigachatApiManager;
        this.gigachatEnvironment = gigachatEnvironment;
        this.encryptionService = encryptionService;
    }


    @Override
    public String getAccessToken() {
        return encryptionService.decrypt(accessToken);
    }

    @Scheduled(fixedDelay = 1_800_000)
    void refreshToken() {
        accessToken = encryptionService.encrypt(gigachatApiManager.getAccessToken(gigachatEnvironment.getGigachatAuthorizationKey()).getAccess_token());
    }
}
