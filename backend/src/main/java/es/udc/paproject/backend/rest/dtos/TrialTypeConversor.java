package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.TrialType;

import java.util.List;
import java.util.stream.Collectors;

public class TrialTypeConversor {

    public static TrialTypeDto toTrialTypeDto(TrialType trialType) {
        return new TrialTypeDto(
                trialType.getId(),
                trialType.getTrialTypeName()
        );
    }

    public final static List<TrialTypeDto> toTrialTypeDtos(List<TrialType> trialTypes) {
        return trialTypes.stream().map(TrialTypeConversor::toTrialTypeDto).collect(Collectors.toList());
    }
}
