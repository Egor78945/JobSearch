package com.example.vacancy_manager_service.service.security;

public interface EncryptionService <E>{
    String encrypt(E toEncrypt);
    E decrypt(String toDecrypt);
}
