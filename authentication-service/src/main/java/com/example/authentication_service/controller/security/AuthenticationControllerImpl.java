package com.example.authentication_service.controller.security;

import com.example.authentication_service.controller.advice.handler.ServiceExceptionHandler;
import com.example.authentication_service.controller.advice.handler.ValidationExceptionHandler;
import com.example.authentication_service.model.keycloak.RefreshTokenModel;
import com.example.authentication_service.model.keycloak.TokenResponse;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.service.AuthenticationService;
import com.example.authentication_service.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@ValidationExceptionHandler
@ServiceExceptionHandler
public class AuthenticationControllerImpl implements AuthenticationController<UserModel, UserModel> {
    protected final RegistrationService<UserModel> registrationService;
    protected final AuthenticationService<UserModel, String, TokenResponse> authenticationService;

    public AuthenticationControllerImpl(RegistrationService<UserModel> registrationService, AuthenticationService<UserModel, String, TokenResponse> authenticationService) {
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserModel registerModel) {
        registrationService.register(registerModel);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody UserModel loginModel) {
        return ResponseEntity.ok(authenticationService.accessToken(loginModel));
    }

    @Override
    @GetMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody RefreshTokenModel refreshTokenModel) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenModel.getToken()));
    }
}
