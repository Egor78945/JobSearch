package com.example.database_manager.exception;

public class NotFoundException extends ServiceException {
    public int code = 404;
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
