package com.example.api_gateway.configuration.security.filter;

import com.example.api_gateway.configuration.security.converter.JwtMapper;
import com.example.api_gateway.configuration.security.request.ServerWebExchangeModificator;
import com.example.api_gateway.model.JwtModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class JwtFilter implements WebFilter {
    private final JwtMapper jwtMapper;
    private final ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator;
    private final JwtDecoder jwtDecoder;

    public JwtFilter(JwtDecoder jwtDecoder, JwtMapper jwtMapper, ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator) {
        this.jwtMapper = jwtMapper;
        this.jwtDecoder = jwtDecoder;
        this.serverWebExchangeModificator = serverWebExchangeModificator;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            List<String> auth = exchange.getRequest().getHeaders().get("Authorization");
            if (auth != null && !auth.isEmpty() && auth.getFirst().startsWith("Bearer ")) {
                String tokenString = auth.getFirst().substring("Bearer ".length());

                Jwt token = jwtDecoder.decode(tokenString);
                JwtModel jwtModel = jwtMapper.mapTo(token);

                exchange = serverWebExchangeModificator.modifyRequestHeaders(exchange, new HttpHeaders(MultiValueMap.fromSingleValue(Map.of("X-User-Uuid", jwtModel.getUser_uuid()))));
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken(jwtModel.getUser_uuid(), null, jwtMapper.convert(token))));
            }
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
