package com.example.vacancy_manager_service.service.api.token;

import com.example.vacancy_manager_service.configuration.HeadHunterEnvironment;
import com.example.vacancy_manager_service.service.api.HeadHunterApiManager;
import com.example.vacancy_manager_service.service.security.EncryptionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HeadHunterApiTokenHandler implements ApiTokenHandler {
    private final HeadHunterApiManager headHunterApiManager;
    private final HeadHunterEnvironment headHunterEnvironment;
    private final EncryptionService<String> encryptionService;
    private volatile String accessToken;

    public HeadHunterApiTokenHandler(HeadHunterApiManager headHunterApiManager, HeadHunterEnvironment headHunterEnvironment, EncryptionService<String> encryptionService) {
        this.headHunterApiManager = headHunterApiManager;
        this.headHunterEnvironment = headHunterEnvironment;
        this.encryptionService = encryptionService;
    }


    @Override
    public String getAccessToken() {
        return encryptionService.decrypt(accessToken);
    }

    @Scheduled(fixedDelay = 1_800_000)
    void refreshToken() {
        accessToken = encryptionService.encrypt(headHunterApiManager.authorize(headHunterEnvironment.getHEAD_HUNTER_CLIENT_ID(), headHunterEnvironment.getHEAD_HUNTER_CLIENT_SECRET()).getAccess_token());
    }
}
