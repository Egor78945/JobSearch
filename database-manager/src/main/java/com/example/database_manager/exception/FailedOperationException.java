package com.example.database_manager.exception;

public class FailedOperationException extends ServiceException {
    public FailedOperationException(String message) {
        super(message);
    }
}
