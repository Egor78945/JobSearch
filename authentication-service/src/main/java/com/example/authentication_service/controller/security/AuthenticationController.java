package com.example.authentication_service.controller.security;

import org.springframework.http.ResponseEntity;

public interface AuthenticationController<L, R> {
    void register(R registerModel);
    ResponseEntity<?> login(L loginModel);
    ResponseEntity<?> refreshToken(String refreshToken);
}
