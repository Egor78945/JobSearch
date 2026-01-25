package com.example.database_manager.exception;

public class AlreadyExistsException extends ServiceException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
