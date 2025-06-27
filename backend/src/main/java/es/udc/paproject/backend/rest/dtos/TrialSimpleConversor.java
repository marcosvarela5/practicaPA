package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Trial;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class TrialSimpleConversor {
    public static TrialSimpleDto toTrialSimpleDto(Trial trial) {
        return new TrialSimpleDto(
                trial.getId(),
                trial.getTrialName(),
                trial.getDateTime().toEpochSecond(ZoneOffset.ofHours(1)),
                trial.getTrialType().getId(),
                trial.getProvince().getId(),
                trial.getAvgScore(),
                (trial.getNumScores() != 0)
        );
    }

    public final static List<TrialSimpleDto> toTrialSimpleDtos(List<Trial> trials) {
        return trials.stream().map(TrialSimpleConversor::toTrialSimpleDto).collect(Collectors.toList());
    }
}
