package com.example.authentication_service.controller.security;

import com.example.authentication_service.model.user.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthenticationController<L, R> {
    void register(R registerModel);
    Mono<ResponseEntity<AuthenticationResponse>> login(L loginModel);
    Mono<ResponseEntity<AuthenticationResponse>> refreshToken(Map<String, String> headers);
}
