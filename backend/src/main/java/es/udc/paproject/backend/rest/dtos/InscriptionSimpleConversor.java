package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Inscription;

public class InscriptionSimpleConversor {
    public final static InscriptionSimpleDto toInscriptionSimpleDto(Inscription inscription) {
        return new InscriptionSimpleDto(
                inscription.getId(),
                inscription.getNumber()
        );
    }
}
