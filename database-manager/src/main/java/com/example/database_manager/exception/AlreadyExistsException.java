package com.example.database_manager.exception;

public class AlreadyExistsException extends ServiceException {
    public int code = 409;
    public AlreadyExistsException(String message) {
        super(message);
    }
    @Override
    public int getCode() {
        return this.code;
    }
}
