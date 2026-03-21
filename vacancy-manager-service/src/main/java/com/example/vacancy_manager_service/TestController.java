package com.example.vacancy_manager_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/vacancy")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test(@RequestHeader Map<String, String> headers) {
        System.out.println("test");
        return ResponseEntity.ok(headers);
    }
}
