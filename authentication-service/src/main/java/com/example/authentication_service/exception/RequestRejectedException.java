package com.example.authentication_service.exception;

public class RequestRejectedException extends ServiceException {
    public RequestRejectedException(String message) {
        super(message);
    }
}
