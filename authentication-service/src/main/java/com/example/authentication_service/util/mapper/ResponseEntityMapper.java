package com.example.authentication_service.util.mapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;


public class ResponseEntityMapper {
    public static <T> ResponseEntity<T> mapToOk(T body, Map<String, String> headers) {
        return ResponseEntity.ok()
                .headers(new HttpHeaders(MultiValueMap.fromSingleValue(headers)))
                .body(body);
    }
}
