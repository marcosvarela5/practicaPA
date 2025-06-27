package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class DuplicateInscriptionException extends Exception {
    private final Long inscriptionId;

    public DuplicateInscriptionException(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }
}
