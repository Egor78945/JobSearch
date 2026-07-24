package com.example.vacancy_manager_service.controller;

import com.example.vacancy_manager_service.configuration.HeadHunterEnvironment;
import com.example.vacancy_manager_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterRelevantVacancyRequest;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterRelevantVacancyResponse;
import com.example.vacancy_manager_service.model.web.head_hunter.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.ReactiveVacancyManagerService;
import com.example.vacancy_manager_service.service.ReactiveVacancySearchService;
import com.example.vacancy_manager_service.service.api.head_hunter.ReactiveHeadHunterApiManager;
import com.example.vacancy_manager_service.service.util.UriUtilities;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/vacancy")
@CommonControllerExceptionHandler
public class VacancyControllerImpl implements ReactiveVacancyController<HeadHunterVacancyResponse.Item> {
    private final ReactiveVacancySearchService<HeadHunterVacancyResponse.Item> vacancyService;
    private final ReactiveVacancyManagerService vacancyManagerService;
    private final HeadHunterEnvironment headHunterEnvironment;
    private final ReactiveHeadHunterApiManager headHunterApiManager;

    public VacancyControllerImpl(ReactiveVacancySearchService<HeadHunterVacancyResponse.Item> vacancyService, ReactiveVacancyManagerService vacancyManagerService, HeadHunterEnvironment headHunterEnvironment, ReactiveHeadHunterApiManager headHunterApiManager) {
        this.vacancyService = vacancyService;
        this.vacancyManagerService = vacancyManagerService;
        this.headHunterEnvironment = headHunterEnvironment;
        this.headHunterApiManager = headHunterApiManager;
    }

    @GetMapping("/token")
    public Mono<HeadHunterAuthorizationResponse> authorize() {
        return headHunterApiManager.authorize(headHunterEnvironment.getHEAD_HUNTER_CLIENT_ID(), headHunterEnvironment.getHEAD_HUNTER_CLIENT_SECRET());
    }

    @GetMapping("/search")
    public Mono<HeadHunterVacancyResponse.Item[]> findByParameters(@RequestParam Map<String, String> parameters) {
        return vacancyService.searchVacancy(UriUtilities.encodeParams(parameters, StandardCharsets.UTF_8));
    }

    @GetMapping("/text")
    public Mono<HeadHunterVacancyResponse.Item[]> findByText(@RequestBody String text) {
        return vacancyService.searchVacancy(text);
    }

    @GetMapping("/relevant")
    public Mono<HeadHunterRelevantVacancyResponse> defineRelevant(@RequestBody HeadHunterRelevantVacancyRequest body) {
        return vacancyManagerService.defineRelevantVacancy(body);
    }
}
