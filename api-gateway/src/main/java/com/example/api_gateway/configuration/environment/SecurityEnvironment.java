package com.example.api_gateway.configuration.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SecurityEnvironment {
    private final Long refreshTokenCookieLifetime;
    private final Set<String> publicPath;

    public SecurityEnvironment(@Value("${token.refresh.cookie-lifetime}") Long refreshTokenCookieLifetime) {
        this.refreshTokenCookieLifetime = refreshTokenCookieLifetime;
        publicPath = Set.of("/auth");
    }

    public Long getRefreshTokenCookieLifetime() {
        return refreshTokenCookieLifetime;
    }

    public boolean isPublicPath(String path) {
        for (String p : publicPath) {
            if(path.startsWith(p)) {
                return true;
            }
        }
        return false;
    }
}
