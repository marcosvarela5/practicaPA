package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class NumberAlreadyDeliveredException extends Exception {
    private final Long inscriptionId;

    public NumberAlreadyDeliveredException(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }
}
