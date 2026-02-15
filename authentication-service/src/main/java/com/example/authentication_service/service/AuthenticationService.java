package com.example.authentication_service.service;

public interface AuthenticationService<A, R, T> {
    T accessToken(A accessTokenModel);
    T refreshToken(R refreshTokenModel);
}
