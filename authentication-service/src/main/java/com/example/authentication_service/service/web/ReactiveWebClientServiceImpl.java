package com.example.authentication_service.service.web;

import org.springframework.http.HttpMethod;
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
        return webClient.method(httpEntity.getMethod() == null ? HttpMethod.GET : httpEntity.getMethod())
                .uri(httpEntity.getUrl())
                .headers(h -> h.addAll(httpEntity.getHeaders()))
                .bodyValue(httpEntity.getBody())
                .retrieve()
                .toEntity(clazz)
                .doOnError(Throwable::printStackTrace)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
