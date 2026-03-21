package com.example.api_gateway.service.authentication;

import com.example.api_gateway.enumeration.Header;
import com.example.api_gateway.model.AuthenticationResponse;
import com.example.api_gateway.service.util.AuthenticationServiceHttpRequestBuilder;
import com.example.api_gateway.service.util.HttpHeaderBuilder;
import com.example.api_gateway.service.web.ReactiveWebClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ReactiveTokenManagerWebService implements ReactiveTokenManagerService<ResponseEntity<AuthenticationResponse>> {
    protected final ReactiveWebClientService webClient;
    protected final AuthenticationServiceHttpRequestBuilder requestBuilder;

    public ReactiveTokenManagerWebService(ReactiveWebClientService webClient, AuthenticationServiceHttpRequestBuilder requestBuilder) {
        this.webClient = webClient;
        this.requestBuilder = requestBuilder;
    }

    @Override
    public Mono<ResponseEntity<AuthenticationResponse>> refreshToken(String refreshToken) {
        return webClient.exchange(requestBuilder.refreshTokenRequest(HttpHeaderBuilder.buildFrom(Map.of(Header.REFRESH_TOKEN_HEADER.getHeaderName(), refreshToken))), AuthenticationResponse.class);
    }
}
