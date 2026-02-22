package com.example.api_gateway.configuration.security.converter;

import com.example.api_gateway.configuration.util.mapper.JsonMapper;
import com.example.api_gateway.model.JwtModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtModelConverter implements Converter<Jwt, JwtModel> {
    private final JsonMapper jsonMapper;

    public JwtModelConverter(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public JwtModel convert(Jwt source) {
        String claims = jsonMapper.mapToString(source.getClaims());
        return jsonMapper.mapFromString(claims, JwtModel.class);
    }
}
