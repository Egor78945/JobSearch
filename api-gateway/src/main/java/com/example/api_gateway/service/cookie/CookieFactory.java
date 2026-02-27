package com.example.api_gateway.service.cookie;

import org.springframework.http.ResponseCookie;

import java.time.Duration;

public interface CookieFactory {
    ResponseCookie createPublic(String cookieName, String value, Duration expires);
}
