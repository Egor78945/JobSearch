package com.example.authentication_service.model.keycloak;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RefreshTokenModel {
    @NotEmpty
    @NotBlank
    @NotNull
    @Size(min = 20, max = 4096)
    private String token;

    public RefreshTokenModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
