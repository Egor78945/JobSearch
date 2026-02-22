package com.example.api_gateway.configuration.security.converter;

import com.example.api_gateway.model.JwtModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private final Converter<Jwt, JwtModel> jwtModelConverter;

    public JwtGrantedAuthoritiesConverter(Converter<Jwt, JwtModel> jwtModelConverter) {
        this.jwtModelConverter = jwtModelConverter;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        return Arrays.stream(Objects.requireNonNull(jwtModelConverter.convert(source)).getRealm_access().getRoles())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
