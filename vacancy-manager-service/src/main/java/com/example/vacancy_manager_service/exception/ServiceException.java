package com.example.vacancy_manager_service.exception;

public class ServiceException extends RuntimeException {
    protected String text;
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String text, Throwable e) {
        super(e);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
