package com.example.authentication_service.controller.security;

import com.example.authentication_service.model.user.security.UserAuthenticationModel;
import org.springframework.http.ResponseEntity;

public class AuthenticationControllerImpl implements AuthenticationController<UserAuthenticationModel, UserAuthenticationModel> {
    @Override
    public ResponseEntity<?> register(UserAuthenticationModel registerModel) {
        return null;
    }

    @Override
    public ResponseEntity<?> login(UserAuthenticationModel loginModel) {
        return null;
    }
}
