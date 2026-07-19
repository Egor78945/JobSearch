package com.example.vacancy_manager_service.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String text) {
        super(text);
    }
}
