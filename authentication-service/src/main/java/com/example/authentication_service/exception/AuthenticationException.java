package com.example.authentication_service.exception;

public class AuthenticationException extends ServiceException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String text, Throwable e) {
        super(text, e);
    }
}
