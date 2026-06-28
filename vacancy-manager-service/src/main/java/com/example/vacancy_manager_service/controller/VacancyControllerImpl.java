package com.example.vacancy_manager_service.controller;

import com.example.vacancy_manager_service.configuration.HeadHunterEnvironment;
import com.example.vacancy_manager_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.vacancy_manager_service.model.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.ReactiveVacancyService;
import com.example.vacancy_manager_service.service.api.ReactiveHeadHunterApiManager;
import com.example.vacancy_manager_service.service.util.UriUtilities;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/vacancy")
@CommonControllerExceptionHandler
public class VacancyControllerImpl implements ReactiveVacancyController<HeadHunterVacancyResponse> {
    private final ReactiveVacancyService<HeadHunterVacancyResponse> vacancyService;
    private final HeadHunterEnvironment headHunterEnvironment;
    private final ReactiveHeadHunterApiManager headHunterApiManager;

    public VacancyControllerImpl(ReactiveVacancyService<HeadHunterVacancyResponse> vacancyService, HeadHunterEnvironment headHunterEnvironment, ReactiveHeadHunterApiManager headHunterApiManager) {
        this.vacancyService = vacancyService;
        this.headHunterEnvironment = headHunterEnvironment;
        this.headHunterApiManager = headHunterApiManager;
    }

    @GetMapping("/token")
    public Mono<HeadHunterAuthorizationResponse> authorize() {
        return headHunterApiManager.authorize(headHunterEnvironment.getHEAD_HUNTER_CLIENT_ID(), headHunterEnvironment.getHEAD_HUNTER_CLIENT_SECRET());
    }

    @GetMapping("/search")
    public Mono<HeadHunterVacancyResponse> findByParameters(@RequestParam Map<String, String> parameters) {
        return vacancyService.searchVacancy(UriUtilities.encodeParams(parameters, StandardCharsets.UTF_8));
    }
}
