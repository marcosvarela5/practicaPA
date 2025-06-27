package es.udc.paproject.backend.rest.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;

public class InscribeParamsDto {
    private String cardNumber;

    public InscribeParamsDto(){}

    @NotNull
    @CreditCardNumber
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
