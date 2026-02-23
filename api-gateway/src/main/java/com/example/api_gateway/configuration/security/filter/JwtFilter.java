package com.example.api_gateway.configuration.security.filter;

import com.example.api_gateway.configuration.security.request.ServerWebExchangeModificator;
import com.example.api_gateway.model.JwtModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class JwtFilter implements WebFilter {
    private final Converter<Jwt, JwtModel> jwtModelConverter;
    private final Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter;
    private final ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator;
    private final JwtDecoder jwtDecoder;

    public JwtFilter(JwtDecoder jwtDecoder, Converter<Jwt, JwtModel> jwtModelConverter, Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter, ServerWebExchangeModificator<ServerWebExchange> serverWebExchangeModificator) {
        this.jwtModelConverter = jwtModelConverter;
        this.authoritiesConverter = authoritiesConverter;
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
                JwtModel jwtModel = jwtModelConverter.convert(token);

                exchange = serverWebExchangeModificator.modifyRequestHeaders(exchange, new HttpHeaders(MultiValueMap.fromSingleValue(Map.of("X-User-Uuid", jwtModel.getUser_uuid()))));
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken(jwtModel.getUser_uuid(), null, authoritiesConverter.convert(token))));
            }
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
