package com.example.vacancy_manager_service.controller;

import com.example.vacancy_manager_service.configuration.HeadHunterEnvironment;
import com.example.vacancy_manager_service.model.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.HeadHunterVacancyResponse;
import com.example.vacancy_manager_service.service.VacancyService;
import com.example.vacancy_manager_service.service.api.HeadHunterApiManager;
import com.example.vacancy_manager_service.service.util.UriUtilities;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/vacancy")
public class VacancyControllerImpl implements VacancyController<HeadHunterVacancyResponse> {
    private final VacancyService<HeadHunterVacancyResponse> vacancyService;
    private final HeadHunterEnvironment headHunterEnvironment;
    private final HeadHunterApiManager headHunterApiManager;

    public VacancyControllerImpl(HeadHunterApiManager headHunterApiManager, VacancyService<HeadHunterVacancyResponse> vacancyService, HeadHunterEnvironment headHunterEnvironment) {
        this.vacancyService = vacancyService;
        this.headHunterApiManager = headHunterApiManager;
        this.headHunterEnvironment = headHunterEnvironment;
    }

    @GetMapping("/token")
    public HeadHunterAuthorizationResponse authorize() {
        return headHunterApiManager.authorize(headHunterEnvironment.getHEAD_HUNTER_CLIENT_ID(), headHunterEnvironment.getHEAD_HUNTER_CLIENT_SECRET());
    }

    @GetMapping("/search")
    public HeadHunterVacancyResponse findByParameters(@RequestParam Map<String, String> parameters) {
        return vacancyService.searchVacancy(UriUtilities.encodeParams(parameters, StandardCharsets.UTF_8));
    }
}
