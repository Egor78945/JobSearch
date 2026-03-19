package com.example.authentication_service.model.web;

import java.util.Map;

public class ErrorResponse {
    private String name;
    private String description;
    private Map<String, String> errors;
    private int status;

    public ErrorResponse(String name, String description, int status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public ErrorResponse(String name, String description, Map<String, String> errors, int status) {
        this.name = name;
        this.description = description;
        this.errors = errors;
        this.status = status;
    }

    public ErrorResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
