package com.example.vacancy_manager_service.controller.advice;

import com.example.vacancy_manager_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.vacancy_manager_service.exception.ValidationException;
import com.example.vacancy_manager_service.exception.WebClientException;
import com.example.vacancy_manager_service.model.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = CommonControllerExceptionHandler.class)
public class CommonControllerAdvice {

    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<ErrorResponse> webClientExceptionHandler(WebClientException e) {
        HttpStatus httpStatus = HttpStatus.valueOf(e.getStatus());
        return new ResponseEntity<>(new ErrorResponse("web client error", e.getMessage(), e.getStatus()), httpStatus);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> validationExceptionHandler(ValidationException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse("validation error", e.getMessage(), e.getInvalid_subjects(), httpStatus.value()), httpStatus);
    }
}
