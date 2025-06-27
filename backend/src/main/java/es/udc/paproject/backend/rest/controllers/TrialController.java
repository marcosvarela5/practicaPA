package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.TrialConversor.toTrialDto;
import static es.udc.paproject.backend.rest.dtos.TrialConversor.toTrialDtos;
import static es.udc.paproject.backend.rest.dtos.TrialSimpleConversor.toTrialSimpleDtos;

import es.udc.paproject.backend.model.entities.Trial;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.TrialService;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.TrialDto;
import es.udc.paproject.backend.rest.dtos.TrialSimpleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/trialSearch")
public class TrialController {
    @Autowired
    private TrialService trialService;

    @GetMapping("/trials")
    public BlockDto<TrialSimpleDto> findTrials(
            @RequestParam(required = false) Long trialTypeId,
            @RequestParam(required = false) Long provinceId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page) {

        Block<Trial> trialBlock = trialService.findTrials
                (trialTypeId, provinceId, startDate, endDate, page, 2);

        return new BlockDto<>(toTrialSimpleDtos(trialBlock.getItems()), trialBlock.getExistMoreItems());
    }


    @GetMapping("/trials/{id}")
    public TrialDto findTrialDetailsById(@PathVariable Long id) throws InstanceNotFoundException {
        return toTrialDto(trialService.findTrialDetailsById(id));
    }


}
