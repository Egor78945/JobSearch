package com.example.authentication_service.controller.security;

import com.example.authentication_service.model.user.security.UserAuthenticationModel;
import com.example.authentication_service.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationControllerImpl implements AuthenticationController<UserAuthenticationModel, UserAuthenticationModel> {
    protected final RegistrationService<UserAuthenticationModel> registrationService;

    public AuthenticationControllerImpl(RegistrationService<UserAuthenticationModel> registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAuthenticationModel registerModel) {
        registrationService.register(registerModel);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/login")
    public ResponseEntity<?> login(UserAuthenticationModel loginModel) {
        return ResponseEntity.notFound().build();
    }
}
