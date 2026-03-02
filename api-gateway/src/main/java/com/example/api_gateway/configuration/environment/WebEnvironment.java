package com.example.api_gateway.configuration.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebEnvironment {
    private final String API_AUTHENTICATION_SERVICE_TOKEN_REFRESH;

    public WebEnvironment(@Value("${api.authentication-service.token-refresh.path}") String apiAuthenticationServiceTokenRefresh) {
        API_AUTHENTICATION_SERVICE_TOKEN_REFRESH = apiAuthenticationServiceTokenRefresh;
    }

    public String getAPI_AUTHENTICATION_SERVICE_TOKEN_REFRESH() {
        return API_AUTHENTICATION_SERVICE_TOKEN_REFRESH;
    }
}
