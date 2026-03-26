package com.example.vacancy_manager_service.service.security;

import com.example.vacancy_manager_service.configuration.security.JasyptEncryptorHandler;
import org.springframework.stereotype.Service;

@Service
public class StringEncryptionService implements EncryptionService<String>{
    private final JasyptEncryptorHandler jasyptEncryptorHandler;

    public StringEncryptionService(JasyptEncryptorHandler jasyptEncryptorHandler) {
        this.jasyptEncryptorHandler = jasyptEncryptorHandler;
    }

    @Override
    public String encrypt(String toEncrypt) {
        return jasyptEncryptorHandler.getTextEncryptor().encrypt(toEncrypt);
    }

    @Override
    public String decrypt(String toDecrypt) {
        return jasyptEncryptorHandler.getTextEncryptor().decrypt(toDecrypt);
    }
}
