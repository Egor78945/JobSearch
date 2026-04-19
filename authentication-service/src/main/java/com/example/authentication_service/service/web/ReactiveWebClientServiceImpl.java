package com.example.authentication_service.service.web;

import com.example.authentication_service.exception.WebClientException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class ReactiveWebClientServiceImpl implements ReactiveWebClientService {
    protected final WebClient webClient;

    public ReactiveWebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <C> Mono<ResponseEntity<C>> exchange(RequestEntity<?> httpEntity, Class<C> clazz) {
        WebClient.RequestBodySpec request = webClient.method(httpEntity.getMethod() == null ? HttpMethod.GET : httpEntity.getMethod())
                .uri(httpEntity.getUrl())
                .headers(h -> h.addAll(httpEntity.getHeaders()));

        if (httpEntity.getBody() != null) {
            request.bodyValue(httpEntity.getBody());
        }
        return request
                .retrieve()
                .toEntity(clazz)
                .onErrorMap(e -> new WebClientException("error while sending http request", HttpStatus.INTERNAL_SERVER_ERROR.value(), e))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
