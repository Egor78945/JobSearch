package com.example.authentication_service.controller.security.advice;

import com.example.authentication_service.controller.advice.handler.AuthenticationExceptionHandler;
import com.example.authentication_service.exception.AuthenticationException;
import com.example.authentication_service.model.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = AuthenticationExceptionHandler.class)
public class AuthenticationControllerAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> unauthorizedExceptionHandler(AuthenticationException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getText(), e.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }
}
