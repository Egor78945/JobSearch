package com.example.vacancy_manager_service.exception;

import java.util.Map;

public class ValidationException extends ServiceException {
    private Map<String, String> invalid_subjects;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Map<String, String> invalid_subjects) {
        super(message);
        this.invalid_subjects = invalid_subjects;
    }

    public Map<String, String> getInvalid_subjects() {
        return invalid_subjects;
    }

    public void setInvalid_subjects(Map<String, String> invalid_subjects) {
        this.invalid_subjects = invalid_subjects;
    }
}
