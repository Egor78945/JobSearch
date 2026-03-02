package com.example.api_gateway.service.authentication;

import com.example.api_gateway.configuration.environment.WebEnvironment;
import com.example.api_gateway.enumeration.Header;
import com.example.api_gateway.model.AuthenticationResponse;
import com.example.api_gateway.service.web.WebClientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Map;

@Service
public class TokenManagerWebServiceImpl implements TokenManagerService<ResponseEntity<AuthenticationResponse>> {
    private final WebClientService webClientService;
    private final WebEnvironment webEnvironment;

    public TokenManagerWebServiceImpl(WebClientService webClientService, WebEnvironment webEnvironment) {
        this.webClientService = webClientService;
        this.webEnvironment = webEnvironment;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> refreshToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders(MultiValueMap.fromSingleValue(Map.of(Header.REFRESH_TOKEN_HEADER.getHeaderName(), refreshToken)));
        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(webEnvironment.getAPI_AUTHENTICATION_SERVICE_TOKEN_REFRESH()));
        return webClientService.exchange(requestEntity, AuthenticationResponse.class);
    }
}
