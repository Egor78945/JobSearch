package com.example.database_manager.exception;

public class FailedOperationException extends ServiceException {
    public int code = 400;
    public FailedOperationException(String message) {
        super(message);
    }
    @Override
    public int getCode() {
        return this.code;
    }
}
