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

import java.util.Map;

@RestController
@RequestMapping("/auth")
@ValidationExceptionHandler
@ServiceExceptionHandler
@AuthenticationExceptionHandler
public class AuthenticationControllerImpl implements AuthenticationController<UserModel, UserModel> {
    protected final RegistrationService<UserModel, UserRegistrationResponse> registrationService;
    protected final TokenManager<UserModel, String, TokenResponse> tokenManager;

    public AuthenticationControllerImpl(RegistrationService<UserModel, UserRegistrationResponse> registrationService, TokenManager<UserModel, String, TokenResponse> tokenManager) {
        this.registrationService = registrationService;
        this.tokenManager = tokenManager;
    }

    @Override
    @PostMapping("/register")
    public void register(@Valid @RequestBody UserModel registerModel) {
        registrationService.register(registerModel);
    }

    @Override
    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody UserModel loginModel) {
        return TokenResponseEntityBuilder.buildToOk(tokenManager.accessToken(loginModel));

    }

    @Override
    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader Map<String, String> headers) {
        return TokenResponseEntityBuilder.buildToOk(tokenManager.refreshToken(headers.get(Header.REFRESH_TOKEN_HEADER.getHeaderName())));
    }
}
