package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Trial;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class TrialConversor {

    public static TrialDto toTrialDto(Trial trial) {
        return new TrialDto(
                trial.getId(),
                trial.getTrialName(),
                trial.getTrialDescription(),
                trial.getDateTime().toEpochSecond(ZoneOffset.ofHours(1)),
                trial.getInscriptionPrice(),
                trial.getMaxParticipants(),
                trial.getNumParticipants(),
                trial.getPlace(),
                trial.getTrialType().getId(),
                trial.getProvince().getId(),
                trial.getAvgScore(),
                (trial.getNumScores() != 0),
                trial.getShowRegister(),
                trial.getShowGiveNumber()
        );
    }

    public final static List<TrialDto> toTrialDtos(List<Trial> trials) {
        return trials.stream().map(TrialConversor::toTrialDto).collect(Collectors.toList());
    }
}
