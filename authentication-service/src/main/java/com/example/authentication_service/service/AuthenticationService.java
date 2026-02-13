package com.example.authentication_service.service;

public interface AuthenticationService<L, T> {
    T authenticate(L loginModel);
}
