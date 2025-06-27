package es.udc.paproject.backend.rest.dtos;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class InscriptionDto {
    private Long id;
    private Long userId;
    private Long trialId;
    private String trialName;
    private int number;
    private String cardNumber;
    private boolean numberDelivered;
    private int score;
    private boolean scored;
    private long dateTime;

    private boolean showRateTrial;

    public InscriptionDto() {
    }

    public InscriptionDto(Long id, Long userId, Long trialId, String trialName, int number, String cardNumber,
                          boolean numberDelivered, int score, boolean scored, long dateTime, boolean showRateTrial) {
        this.id = id;
        this.userId = userId;
        this.trialId = trialId;
        this.trialName = trialName;
        this.number = number;
        this.cardNumber = cardNumber;
        this.numberDelivered = numberDelivered;
        this.score = score;
        this.scored = scored;
        this.dateTime = dateTime;
        this.showRateTrial = showRateTrial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTrialId() {
        return trialId;
    }

    public void setTrialId(Long trialId) {
        this.trialId = trialId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isNumberDelivered() {
        return numberDelivered;
    }

    public void setNumberDelivered(boolean numberDelivered) {
        this.numberDelivered = numberDelivered;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getTrialName() {
        return trialName;
    }

    public void setTrialName(String trialName) {
        this.trialName = trialName;
    }

    public boolean isShowRateTrial() {
        return showRateTrial;
    }

    public void setShowRateTrial(boolean showRateTrial) {
        this.showRateTrial = showRateTrial;
    }
}
