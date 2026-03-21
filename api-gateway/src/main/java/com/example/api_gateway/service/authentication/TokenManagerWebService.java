package com.example.api_gateway.service.authentication;

import com.example.api_gateway.enumeration.Header;
import com.example.api_gateway.model.AuthenticationResponse;
import com.example.api_gateway.service.util.AuthenticationServiceHttpRequestBuilder;
import com.example.api_gateway.service.util.HttpHeaderBuilder;
import com.example.api_gateway.service.web.WebClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenManagerWebService implements TokenManagerService<ResponseEntity<AuthenticationResponse>> {
    private final WebClientService webClientService;
    private final AuthenticationServiceHttpRequestBuilder requestBuilder;

    public TokenManagerWebService(WebClientService webClientService, AuthenticationServiceHttpRequestBuilder requestBuilder) {
        this.webClientService = webClientService;
        this.requestBuilder = requestBuilder;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> refreshToken(String refreshToken) {
        return webClientService.exchange(requestBuilder.refreshTokenRequest(HttpHeaderBuilder.buildFrom(Map.of(Header.REFRESH_TOKEN_HEADER.getHeaderName(), refreshToken))), AuthenticationResponse.class);
    }
}
