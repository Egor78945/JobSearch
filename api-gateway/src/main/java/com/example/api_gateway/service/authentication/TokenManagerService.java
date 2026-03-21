package com.example.api_gateway.service.authentication;

public interface TokenManagerService<T> {
    T refreshToken(String refreshToken);
}
