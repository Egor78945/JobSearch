package com.example.authentication_service.controller.security;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthenticationController<L, R> {
    void register(R registerModel);
    ResponseEntity<?> login(L loginModel);
    ResponseEntity<?> refreshToken(Map<String, String> headers);
}
