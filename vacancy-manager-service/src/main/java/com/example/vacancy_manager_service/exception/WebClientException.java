package com.example.vacancy_manager_service.exception;


public class WebClientException extends ServiceException {
    private int status;
    public WebClientException(String message, int status) {
        super(message);
        this.status = status;
    }

    public WebClientException(String text, int status, Throwable e) {
        super(text);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
