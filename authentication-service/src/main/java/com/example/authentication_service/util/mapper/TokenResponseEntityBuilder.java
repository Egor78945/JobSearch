package com.example.authentication_service.util.mapper;

import com.example.authentication_service.enumeration.Header;
import com.example.authentication_service.model.keycloak.TokenResponse;
import com.example.authentication_service.model.user.AuthenticationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;


public class TokenResponseEntityBuilder {
    public static ResponseEntity<AuthenticationResponse> buildToOk(TokenResponse tokenResponse) {
        return ResponseEntity.ok()
                .headers(new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(Header.REFRESH_TOKEN_HEADER.getHeaderName(), tokenResponse.getRefreshToken()))))
                .body(new AuthenticationResponse(tokenResponse.getAccessToken(), tokenResponse.getExpiresIn()));
    }
}
