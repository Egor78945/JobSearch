package com.example.authentication_service.controller.security;

import com.example.authentication_service.controller.advice.handler.ServiceExceptionHandler;
import com.example.authentication_service.controller.advice.handler.ValidationExceptionHandler;
import com.example.authentication_service.enumeration.Header;
import com.example.authentication_service.model.keycloak.TokenResponse;
import com.example.authentication_service.model.user.AuthenticationResponse;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.model.user.UserRegistrationResponse;
import com.example.authentication_service.service.AuthenticationService;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.util.mapper.ResponseEntityMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@ValidationExceptionHandler
@ServiceExceptionHandler
public class AuthenticationControllerImpl implements AuthenticationController<UserModel, UserModel> {
    protected final RegistrationService<UserModel, UserRegistrationResponse> registrationService;
    protected final AuthenticationService<UserModel, String, TokenResponse> authenticationService;

    public AuthenticationControllerImpl(RegistrationService<UserModel, UserRegistrationResponse> registrationService, AuthenticationService<UserModel, String, TokenResponse> authenticationService) {
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
    }

    @Override
    @PostMapping("/register")
    public void register(@Valid @RequestBody UserModel registerModel) {
        registrationService.register(registerModel);
    }

    @Override
    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody UserModel loginModel) {
        TokenResponse tokenResponse = authenticationService.accessToken(loginModel);
        return ResponseEntityMapper.mapToOk(new AuthenticationResponse(tokenResponse.getAccessToken(), tokenResponse.getExpiresIn()), Map.of(Header.REFRESH_TOKEN_HEADER.getHeaderName(), tokenResponse.getRefreshToken()));

    }

    @Override
    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader Map<String, String> headers) {
        TokenResponse tokenResponse = authenticationService.refreshToken(headers.get(Header.REFRESH_TOKEN_HEADER.getHeaderName()));
        return ResponseEntityMapper.mapToOk(new AuthenticationResponse(tokenResponse.getAccessToken(), tokenResponse.getExpiresIn()), Map.of(Header.REFRESH_TOKEN_HEADER.getHeaderName(), tokenResponse.getRefreshToken()));
    }
}
