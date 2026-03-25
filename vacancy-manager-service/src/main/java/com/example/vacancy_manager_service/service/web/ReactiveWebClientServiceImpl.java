package com.example.vacancy_manager_service.service.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReactiveWebClientServiceImpl implements ReactiveWebClientService {
    protected final WebClient webClient;

    public ReactiveWebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <C> Mono<ResponseEntity<C>> exchange(RequestEntity<?> httpEntity, Class<C> clazz) {
        return webClient.get()
                .uri(httpEntity.getUrl())
                .headers(h -> h.addAll(httpEntity.getHeaders()))
                .retrieve()
                .toEntity(clazz)
                .doOnError(Throwable::printStackTrace);
    }
}
