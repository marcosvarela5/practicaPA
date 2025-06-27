package es.udc.paproject.backend.model.exceptions;

public class IncorrectTrialException extends Exception {

    private final Long inscriptionTrialId;
    private final Long trialId;

    public IncorrectTrialException(Long inscriptionTrialId, Long trialId) {
        this.inscriptionTrialId = inscriptionTrialId;
        this.trialId = trialId;
    }

    public Long getInscriptionTrialId() {
        return inscriptionTrialId;
    }

    public Long getTrialId() {
        return trialId;
    }
}
