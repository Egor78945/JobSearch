package com.example.api_gateway.configuration.security;

import com.example.api_gateway.configuration.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfigurationImpl implements WebSecurityConfiguration {
    private final JwtFilter jwtFilter;
    private final Converter<Jwt, Mono<AbstractAuthenticationToken>> converter;

    public WebSecurityConfigurationImpl(JwtFilter jwtFilter, Converter<Jwt, Mono<AbstractAuthenticationToken>> converter) {
        this.jwtFilter = jwtFilter;
        this.converter = converter;
    }

    @Override
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println(http);
        return http.authorizeExchange(exchange ->
                        exchange.pathMatchers("/auth/**").permitAll()
                                .anyExchange().authenticated())
                .oauth2ResourceServer(oa2 ->
                        oa2.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)))
                .addFilterBefore(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .build();
    }
}
