package com.example.vacancy_manager_service.configuration.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class JasyptEncryptorHandler {
    private final BasicTextEncryptor textEncryptor;

    public JasyptEncryptorHandler(EncryptionEnvironment encryptionEnvironment) {
        this.textEncryptor = new BasicTextEncryptor();
        this.textEncryptor.setPassword(encryptionEnvironment.getSecret());
    }

    public BasicTextEncryptor getTextEncryptor() {
        return textEncryptor;
    }
}
