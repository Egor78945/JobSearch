package com.example.api_gateway.service.processor;

import com.example.api_gateway.configuration.environment.SecurityEnvironment;
import com.example.api_gateway.configuration.security.converter.JwtMapper;
import com.example.api_gateway.configuration.security.request.ServerWebExchangeModificator;
import com.example.api_gateway.enumeration.Cookie;
import com.example.api_gateway.enumeration.Header;
import com.example.api_gateway.model.AuthenticationResponse;
import com.example.api_gateway.model.JwtModel;
import com.example.api_gateway.service.authentication.ReactiveTokenManagerService;
import com.example.api_gateway.service.cookie.CookieFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Component
public class ReactiveAuthenticationHttpRequestProcessor implements ReactiveHttpRequestProcessor<Void> {
    private final JwtMapper jwtMapper;
    private final ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator;
    private final ReactiveTokenManagerService<ResponseEntity<AuthenticationResponse>> tokenManagerService;
    private final SecurityEnvironment securityEnvironment;
    private final CookieFactory cookieFactory;
    private final JwtDecoder jwtDecoder;

    public ReactiveAuthenticationHttpRequestProcessor(JwtDecoder jwtDecoder, JwtMapper jwtMapper, ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator, ReactiveTokenManagerService<ResponseEntity<AuthenticationResponse>> tokenManagerService, SecurityEnvironment securityEnvironment, CookieFactory cookieFactory) {
        this.jwtMapper = jwtMapper;
        this.jwtDecoder = jwtDecoder;
        this.serverWebExchangeModificator = serverWebExchangeModificator;
        this.tokenManagerService = tokenManagerService;
        this.securityEnvironment = securityEnvironment;
        this.cookieFactory = cookieFactory;
    }

    @Override
    public Mono<Void> process(ServerWebExchange exchange, WebFilterChain chain) {
        return extractAuthorization(exchange)
                .flatMap(t -> apply(t, exchange, chain))
                .onErrorResume(JwtException.class, t -> handleJwtError(exchange, chain))
                .onErrorResume(Exception.class, t -> handleAnotherError(exchange));

    }

    private Mono<String> extractAuthorization(ServerWebExchange exchange) {
        return Mono
                .justOrEmpty(exchange.getRequest().getHeaders().get(Header.AUTHORIZATION_HEADER.getHeaderName()))
                .switchIfEmpty(Mono.error(new JwtException("Missing authentication token")))
                .filter(h -> !h.isEmpty() && h.getFirst().startsWith("Bearer "))
                .map(a -> a.getFirst().substring("Bearer ".length()));
    }

    private Mono<Void> apply(String token, ServerWebExchange exchange, WebFilterChain chain) {
        return decodeToken(token)
                .flatMap(t -> setupSecurityContext(t, exchange, chain));
    }

    private Mono<Jwt> decodeToken(String token) {
        return Mono.just(jwtDecoder.decode(token))
                .doOnError(Mono::error);
    }

    private Mono<Void> setupSecurityContext(Jwt token, ServerWebExchange exchange, WebFilterChain chain) {
        try {
            JwtModel jwtModel = jwtMapper.mapTo(token);

            exchange = serverWebExchangeModificator.modifyRequestHeaders(exchange, new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(Header.USER_UUID_HEADER.getHeaderName(), jwtModel.getUser_uuid(), Header.AUTHORIZATION_HEADER.getHeaderName(), String.format("Bearer %s", token.getTokenValue())))));

            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken(jwtModel.getUser_uuid(), null, jwtMapper.convert(token))));
        } catch (Exception e) {
            return Mono.error(e);
        }

    }

    private Mono<Void> handleJwtError(ServerWebExchange exchange, WebFilterChain chain) {
        return refreshToken(exchange)
                .flatMap(t -> updateRefreshTokenAndProceed(t, exchange, chain));
    }

    private Mono<ResponseEntity<AuthenticationResponse>> refreshToken(ServerWebExchange exchange) {
        return extractRefreshToken(exchange)
                .switchIfEmpty(Mono.error(() -> new IllegalArgumentException("invalid refresh token")))
                .flatMap(tokenManagerService::refreshToken);
    }

    private Mono<String> extractRefreshToken(ServerWebExchange exchange) {
        String refreshToken = exchange.getRequest().getCookies().getFirst(Cookie.REFRESH_TOKEN_COOKIE.getCookieName()).getValue();
        return Mono.justOrEmpty(refreshToken.isEmpty() ? null : refreshToken);
    }

    private Mono<Void> updateRefreshTokenAndProceed(ResponseEntity<AuthenticationResponse> response, ServerWebExchange exchange, WebFilterChain chain) {
        ServerWebExchange modified = serverWebExchangeModificator.modifyResponseCookie(exchange, cookieFactory.createPublic(Cookie.REFRESH_TOKEN_COOKIE.getCookieName(), response.getHeaders().getFirst(Header.REFRESH_TOKEN_HEADER.getHeaderName()), Duration.ofDays(securityEnvironment.getRefreshTokenCookieLifetime())));

        return decodeToken(response.getBody().getAccessToken())
                .flatMap(t -> setupSecurityContext(t, modified, chain));
    }

    private Mono<Void> handleAnotherError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
