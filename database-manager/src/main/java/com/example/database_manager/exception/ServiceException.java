package com.example.database_manager.exception;

public class ServiceException extends RuntimeException {
    public int code = 500;
    public ServiceException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }
}
