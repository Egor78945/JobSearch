package com.example.api_gateway.service.authentication;

import org.springframework.http.ResponseEntity;

public interface TokenManagerService<T> {
    T refreshToken(String refreshToken);
}
