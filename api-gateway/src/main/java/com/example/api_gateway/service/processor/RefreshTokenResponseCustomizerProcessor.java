package com.example.api_gateway.service.processor;

import com.example.api_gateway.configuration.environment.SecurityEnvironment;
import com.example.api_gateway.enumeration.Cookie;
import com.example.api_gateway.service.cookie.CookieFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

import static com.example.api_gateway.enumeration.Header.REFRESH_TOKEN_HEADER;

@Component
public class RefreshTokenResponseCustomizerProcessor implements HttpResponseProcessor<ServerHttpResponse> {
    protected final CookieFactory cookieFactory;
    protected final SecurityEnvironment securityEnvironment;

    public RefreshTokenResponseCustomizerProcessor(CookieFactory cookieFactory, SecurityEnvironment securityEnvironment) {
        this.cookieFactory = cookieFactory;
        this.securityEnvironment = securityEnvironment;
    }

    @Override
    public void process(ServerHttpResponse response) {
        List<String> refreshTokenHeaders = response.getHeaders().get(REFRESH_TOKEN_HEADER.getHeaderName());

        if (refreshTokenHeaders != null && !refreshTokenHeaders.isEmpty()) {
            String refreshToken = refreshTokenHeaders.getFirst();

            ResponseCookie cookie = cookieFactory.createPublic(Cookie.REFRESH_TOKEN_COOKIE.getCookieName(), refreshToken, Duration.ofDays(securityEnvironment.getRefreshTokenCookieLifetime()));
            response.addCookie(cookie);

            response.getHeaders().remove(REFRESH_TOKEN_HEADER.getHeaderName());
        }
    }
}
