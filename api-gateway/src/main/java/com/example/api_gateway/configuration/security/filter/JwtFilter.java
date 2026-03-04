package com.example.api_gateway.configuration.security.filter;

import com.example.api_gateway.configuration.environment.SecurityEnvironment;
import com.example.api_gateway.configuration.security.converter.JwtMapper;
import com.example.api_gateway.configuration.security.request.ServerWebExchangeModificator;
import com.example.api_gateway.enumeration.Cookie;
import com.example.api_gateway.enumeration.Header;
import com.example.api_gateway.model.AuthenticationResponse;
import com.example.api_gateway.model.JwtModel;
import com.example.api_gateway.service.authentication.TokenManagerService;
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
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Component
public class JwtFilter implements WebFilter {
    private final JwtMapper jwtMapper;
    private final ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator;
    private final TokenManagerService<ResponseEntity<AuthenticationResponse>> tokenManagerService;
    private final SecurityEnvironment securityEnvironment;
    private final CookieFactory cookieFactory;
    private final JwtDecoder jwtDecoder;

    public JwtFilter(JwtDecoder jwtDecoder, JwtMapper jwtMapper, ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator, TokenManagerService<ResponseEntity<AuthenticationResponse>> tokenManagerService, SecurityEnvironment securityEnvironment, CookieFactory cookieFactory) {
        this.jwtMapper = jwtMapper;
        this.jwtDecoder = jwtDecoder;
        this.serverWebExchangeModificator = serverWebExchangeModificator;
        this.tokenManagerService = tokenManagerService;
        this.securityEnvironment = securityEnvironment;
        this.cookieFactory = cookieFactory;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            List<String> auth = exchange.getRequest().getHeaders().get("Authorization");
            if (auth != null && !auth.isEmpty() && auth.getFirst().startsWith("Bearer ")) {
                String tokenString = auth.getFirst().substring("Bearer ".length());

                Jwt token;
                try {
                    token = jwtDecoder.decode(tokenString);
                } catch (JwtException e) {
                    String refresh = exchange.getRequest().getCookies().getFirst(Cookie.REFRESH_TOKEN_COOKIE.getCookieName()).getValue();
                    ResponseEntity<AuthenticationResponse> response = tokenManagerService.refreshToken(refresh);

                    exchange = serverWebExchangeModificator.modifyResponseCookie(exchange, cookieFactory.createPublic(Cookie.REFRESH_TOKEN_COOKIE.getCookieName(), response.getHeaders().getFirst(Header.REFRESH_TOKEN_HEADER.getHeaderName()), Duration.ofDays(securityEnvironment.getRefreshTokenCookieLifetime())));

                    token = jwtDecoder.decode(response.getBody().getAccessToken());
                }

                JwtModel jwtModel = jwtMapper.mapTo(token);

                exchange = serverWebExchangeModificator.modifyRequestHeaders(exchange, new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(Header.USER_UUID_HEADER.getHeaderName(), jwtModel.getUser_uuid(), "Authorization", String.format("Bearer %s", token.getTokenValue())))));
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken(jwtModel.getUser_uuid(), null, jwtMapper.convert(token))));
            }
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
