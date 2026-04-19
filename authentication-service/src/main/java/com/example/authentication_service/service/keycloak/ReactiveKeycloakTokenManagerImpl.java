package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.ReactiveKeycloakFactory;
import com.example.authentication_service.configuration.keycloak.client.KeycloakClientConfiguration;
import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.exception.AuthenticationException;
import com.example.authentication_service.exception.FailedOperationException;
import com.example.authentication_service.model.keycloak.TokenResponse;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.service.TokenManager;
import com.example.authentication_service.service.keycloak.request.factory.KeycloakTokenRequestFactory;
import com.example.authentication_service.service.web.ReactiveWebClientService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveKeycloakTokenManagerImpl implements TokenManager<UserModel, String, Mono<TokenResponse>> {
    protected final ReactiveKeycloakFactory keycloakFactory;
    protected final ReactiveWebClientService webClientService;
    protected final KeycloakEnvironment keycloakEnvironment;
    protected final KeycloakClientConfiguration clientConfiguration;
    protected final ReactiveKeycloakResourceManager keycloakResourceManager;
    protected final KeycloakTokenRequestFactory tokenRequestFactory;

    public ReactiveKeycloakTokenManagerImpl(ReactiveKeycloakFactory keycloakFactory, ReactiveWebClientService webClientService, KeycloakEnvironment keycloakEnvironment, KeycloakClientConfiguration clientConfiguration, ReactiveKeycloakResourceManager keycloakResourceManager, KeycloakTokenRequestFactory tokenRequestFactory) {
        this.keycloakFactory = keycloakFactory;
        this.webClientService = webClientService;
        this.keycloakEnvironment = keycloakEnvironment;
        this.clientConfiguration = clientConfiguration;
        this.keycloakResourceManager = keycloakResourceManager;
        this.tokenRequestFactory = tokenRequestFactory;
    }

    @Override
    public Mono<TokenResponse> accessToken(UserModel userModel) {
        return keycloakFactory.create(userModel.getEmail(), userModel.getPassword())
                .flatMap(k -> Mono.fromCallable(() -> k.tokenManager().getAccessToken())
                        .subscribeOn(Schedulers.boundedElastic()))
                .map(token ->
                        new TokenResponse(token.getToken(), token.getRefreshToken(), token.getExpiresIn(), token.getRefreshExpiresIn()))
                .onErrorMap(e -> new AuthenticationException("failed to get access token", e));
    }

    @Override
    public Mono<TokenResponse> refreshToken(String refreshToken) {
        return keycloakResourceManager.clientRepresentation(keycloakEnvironment.getAuthenticationRealmName(), keycloakEnvironment.getAuthenticationClientId())
                .map(client -> tokenRequestFactory.refreshTokenRequest(client.getClientId(), client.getSecret(), refreshToken))
                .flatMap(request -> webClientService.exchange(new RequestEntity<>(request.getBody(), request.getHeaders(), HttpMethod.POST, clientConfiguration.tokenUri(keycloakEnvironment.getAuthenticationRealmName())), TokenResponse.class))
                .mapNotNull(HttpEntity::getBody)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("empty refresh-token response")))
                .onErrorMap(e -> new FailedOperationException("failed to refresh token", e));
    }
}
