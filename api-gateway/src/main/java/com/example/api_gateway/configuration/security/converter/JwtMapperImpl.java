package com.example.api_gateway.configuration.security.converter;

import com.example.api_gateway.configuration.util.mapper.JsonMapper;
import com.example.api_gateway.model.JwtModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtMapperImpl implements JwtMapper {
    private final JsonMapper jsonMapper;

    public JwtMapperImpl(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public JwtModel mapTo(Jwt source) {
        String claims = jsonMapper.mapToString(source.getClaims());
        return jsonMapper.mapFromString(claims, JwtModel.class);
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        return Arrays.stream(Objects.requireNonNull(mapTo(source)).getRealm_access().getRoles())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
