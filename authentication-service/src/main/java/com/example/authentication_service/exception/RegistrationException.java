package com.example.authentication_service.exception;

public class RegistrationException extends ServiceException{
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String text, Throwable e) {
        super(text, e);
    }
}
