package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class InvalidCreditCardException extends Exception {
    private final Long inscriptionId;
    private final String creditCard;
    public InvalidCreditCardException(Long inscriptionId, String creditCard){
        this.inscriptionId = inscriptionId;
        this.creditCard = creditCard;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }

    public String getCreditCard() {
        return creditCard;
    }
}
