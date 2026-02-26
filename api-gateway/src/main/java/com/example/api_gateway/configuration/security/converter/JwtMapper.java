package com.example.api_gateway.configuration.security.converter;

import com.example.api_gateway.model.JwtModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

public interface JwtMapper extends Converter<Jwt, Collection<GrantedAuthority>> {
    JwtModel mapTo(Jwt source);
}
