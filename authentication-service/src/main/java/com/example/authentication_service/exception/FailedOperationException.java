package com.example.authentication_service.exception;

public class FailedOperationException extends ServiceException{
    public FailedOperationException(String message) {
        super(message);
    }

    public FailedOperationException(String text, Throwable e) {
        super(text, e);
    }
}
