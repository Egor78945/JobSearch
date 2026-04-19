package com.example.authentication_service.controller.advice;

import com.example.authentication_service.controller.advice.handler.ServiceExceptionHandler;
import com.example.authentication_service.controller.advice.handler.ValidationExceptionHandler;
import com.example.authentication_service.exception.ServiceException;
import com.example.authentication_service.exception.WebClientException;
import com.example.authentication_service.model.web.ErrorResponse;
import com.example.authentication_service.util.mapper.StatusCodeMapper;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = {ValidationExceptionHandler.class, ServiceExceptionHandler.class})
public class CommonControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationExceptionHandler(MethodArgumentNotValidException e) {
        var map = new HashMap<String, String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse("invalidated", "Invalid data format", map, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<ErrorResponse> statusRuntimeExceptionHandler(StatusRuntimeException e) {
        HttpStatus status = StatusCodeMapper.toCode(e.getStatus().getCode().value());
        return new ResponseEntity<>(new ErrorResponse(status.name().toLowerCase(), e.getMessage(), status.value()), status);
    }

    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<ErrorResponse> webClientExceptionHandler(WebClientException e) {
        HttpStatus status = HttpStatus.valueOf(e.getStatus());
        return new ResponseEntity<>(new ErrorResponse(e.getText(), e.getMessage(), status.value()), status);
    }
}
