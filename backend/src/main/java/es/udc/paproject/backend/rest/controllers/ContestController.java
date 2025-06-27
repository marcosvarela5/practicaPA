package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.services.ContestService;
import es.udc.paproject.backend.rest.dtos.ProvinceDto;
import es.udc.paproject.backend.rest.dtos.TrialTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static es.udc.paproject.backend.rest.dtos.ProvinceConversor.toProvinceDtos;
import static es.udc.paproject.backend.rest.dtos.TrialTypeConversor.toTrialTypeDtos;

@RestController
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @GetMapping("/provinces")
    public List<ProvinceDto> findAllProvinces() {
        return toProvinceDtos(contestService.findAllProvinces());
    }

    @GetMapping("/trialTypes")
    public List<TrialTypeDto> findAllTrialTypes() {
        return toTrialTypeDtos(contestService.findAllTrialTypes());
    }
}
