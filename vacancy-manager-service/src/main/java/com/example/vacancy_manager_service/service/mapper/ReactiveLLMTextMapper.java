package com.example.vacancy_manager_service.service.mapper;

import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyRequest;
import reactor.core.publisher.Mono;

public interface ReactiveLLMTextMapper {
    <T> Mono<T> mapFromString(String text, Class<T> type);
}
