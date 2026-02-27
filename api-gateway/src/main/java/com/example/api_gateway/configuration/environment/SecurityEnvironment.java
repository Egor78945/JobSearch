package com.example.api_gateway.configuration.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityEnvironment {
    private final Long refreshTokenCookieLifetime;

    public SecurityEnvironment(@Value("${token.refresh.cookie-lifetime}") Long refreshTokenCookieLifetime) {
        this.refreshTokenCookieLifetime = refreshTokenCookieLifetime;
    }

    public Long getRefreshTokenCookieLifetime() {
        return refreshTokenCookieLifetime;
    }
}
