package com.example.authentication_service.controller.security;

import com.example.authentication_service.controller.advice.handler.AuthenticationExceptionHandler;
import com.example.authentication_service.controller.advice.handler.ServiceExceptionHandler;
import com.example.authentication_service.controller.advice.handler.ValidationExceptionHandler;
import com.example.authentication_service.enumeration.Header;
import com.example.authentication_service.model.keycloak.TokenResponse;
import com.example.authentication_service.model.user.AuthenticationResponse;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.model.user.UserRegistrationResponse;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.TokenManager;
import com.example.authentication_service.util.mapper.TokenResponseEntityBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@ValidationExceptionHandler
@ServiceExceptionHandler
@AuthenticationExceptionHandler
public class AuthenticationControllerImpl implements AuthenticationController<UserModel, UserModel> {
    protected final RegistrationService<UserModel, Mono<UserRegistrationResponse>> registrationService;
    protected final TokenManager<UserModel, String, Mono<TokenResponse>> tokenManager;

    public AuthenticationControllerImpl(RegistrationService<UserModel, Mono<UserRegistrationResponse>> registrationService, TokenManager<UserModel, String, Mono<TokenResponse>> tokenManager) {
        this.registrationService = registrationService;
        this.tokenManager = tokenManager;
    }

    @Override
    @PostMapping("/register")
    public Mono<UserRegistrationResponse> register(@Valid @RequestBody UserModel registerModel) {
        return registrationService.register(registerModel);
    }

    @Override
    @GetMapping("/login")
    public Mono<ResponseEntity<AuthenticationResponse>> login(@Valid @RequestBody UserModel loginModel) {
        return tokenManager.accessToken(loginModel)
                .map(TokenResponseEntityBuilder::buildToOk);

    }

    @Override
    @GetMapping("/refresh")
    public Mono<ResponseEntity<AuthenticationResponse>> refreshToken(@RequestHeader Map<String, String> headers) {
        return tokenManager.refreshToken(headers.get(Header.REFRESH_TOKEN_HEADER.getHeaderName()))
                .map(TokenResponseEntityBuilder::buildToOk);
    }
}
