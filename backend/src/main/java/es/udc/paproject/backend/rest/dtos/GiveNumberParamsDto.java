package es.udc.paproject.backend.rest.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;

public class GiveNumberParamsDto {
    private String cardNumber;
    private Long inscriptionId;

    public GiveNumberParamsDto(){}

    @NotNull
    @CreditCardNumber
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @NotNull
    public Long getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }
}
