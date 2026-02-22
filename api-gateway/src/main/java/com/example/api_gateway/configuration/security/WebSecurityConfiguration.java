package com.example.api_gateway.configuration.security;

import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

public interface WebSecurityConfiguration {
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http);
}
