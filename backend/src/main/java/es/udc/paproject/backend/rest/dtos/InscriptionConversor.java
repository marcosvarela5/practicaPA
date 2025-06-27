package es.udc.paproject.backend.rest.dtos;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Inscription;

public class InscriptionConversor {

    public final static InscriptionDto toInscriptionDto(Inscription inscription) {
        return new InscriptionDto(
                inscription.getId(),
                inscription.getUser().getId(),
                inscription.getTrial().getId(),
                inscription.getTrial().getTrialName(),
                inscription.getNumber(),
                inscription.getCardNumber().substring(inscription.getCardNumber().length() - 4), // last 4 digits
                inscription.isNumberDelivered(),
                inscription.getScore(),
                inscription.isScored(),
                inscription.getDateTime().toEpochSecond(ZoneOffset.ofHours(1)),
                inscription.getShowRateTrial()
        );
    }

    public final static List<InscriptionDto> toInscriptionDtos(List<Inscription> inscriptions) {
        return inscriptions.stream().map(InscriptionConversor::toInscriptionDto).collect(Collectors.toList());
    }



}
