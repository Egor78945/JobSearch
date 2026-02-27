package com.example.api_gateway.service.cookie;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CookieFactoryImpl implements CookieFactory {
    @Override
    public ResponseCookie createPublic(String cookieName, String value, Duration expires) {
        return ResponseCookie.from(cookieName, value)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(expires)
                .sameSite("Lax")
                .build();
    }
}
