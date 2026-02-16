package com.example.authentication_service.controller.security;

import com.example.authentication_service.model.keycloak.RefreshTokenModel;
import org.springframework.http.ResponseEntity;

public interface AuthenticationController<L, R> {
    ResponseEntity<?> register(R registerModel);
    ResponseEntity<?> login(L loginModel);
    ResponseEntity<?> refreshToken(RefreshTokenModel refreshToken);
}
