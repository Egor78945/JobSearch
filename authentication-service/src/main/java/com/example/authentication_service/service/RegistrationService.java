package com.example.authentication_service.service;

public interface RegistrationService<R, A> {
    A register(R registerRequest);
}
