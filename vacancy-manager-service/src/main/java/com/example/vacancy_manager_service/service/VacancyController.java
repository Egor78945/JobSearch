package com.example.vacancy_manager_service.service;

import com.example.vacancy_manager_service.configuration.HeadHunterEnvironment;
import com.example.vacancy_manager_service.enumeration.HeadHunterVacancyParameter;
import com.example.vacancy_manager_service.model.HeadHunterAuthorizationResponse;
import com.example.vacancy_manager_service.model.HeadHunterVacancyResponse;
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
public class VacancyController implements VacancyService<HeadHunterVacancyResponse> {
    private final HeadHunterApiManager headHunterApiManager;
    private final HeadHunterEnvironment headHunterEnvironment;

    public VacancyController(HeadHunterApiManager headHunterApiManager, HeadHunterEnvironment headHunterEnvironment) {
        this.headHunterApiManager = headHunterApiManager;
        this.headHunterEnvironment = headHunterEnvironment;
    }

    @GetMapping("/token")
    public HeadHunterAuthorizationResponse authorize() {
        return headHunterApiManager.authorize(headHunterEnvironment.getHEAD_HUNTER_CLIENT_ID(), headHunterEnvironment.getHEAD_HUNTER_CLIENT_SECRET());
    }

    @GetMapping("/search")
    public HeadHunterVacancyResponse findByParameters(@RequestParam Map<String, String> parameters) {
        return headHunterApiManager.vacancySearch(HeadHunterVacancyParameter.mapFromString(UriUtilities.encodeParams(parameters, StandardCharsets.UTF_8)));
    }
}
