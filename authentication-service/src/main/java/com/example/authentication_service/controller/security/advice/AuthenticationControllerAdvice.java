package com.example.authentication_service.controller.security.advice;

import com.example.authentication_service.controller.advice.handler.AuthenticationExceptionHandler;
import com.example.authentication_service.model.web.ErrorResponse;
import jakarta.ws.rs.NotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = AuthenticationExceptionHandler.class)
public class AuthenticationControllerAdvice {

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorizedExceptionHandler() {
        return new ResponseEntity<>(new ErrorResponse("Unauthorized", "Wrong username or password", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }
}
