package es.udc.paproject.backend.rest.dtos;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TrialDto {
    private Long id;
    private String trialName;
    private Long trialTypeId;
    private float avgScore;
    private boolean hasScores;
    private String place;
    private Long provinceId;
    private long dateTime;
    private BigDecimal inscriptionPrice;
    private int maxParticipants;
    private int numParticipants;
    private String trialDescription;
    private boolean showRegister;
    private boolean showGiveNumber;


    public TrialDto() {
    }

    public TrialDto(Long id, String trialName, String trialDescription, Long dateTime,
                    BigDecimal inscriptionPrice, int maxParticipants, int numParticipants, String place,
                    Long trialTypeId, Long provinceId, float avgScore, boolean hasScores, boolean showRegister,
                    boolean showGiveNumber) {
        this.id = id;
        this.trialName = trialName;
        this.trialDescription = trialDescription;
        this.dateTime = dateTime;
        this.inscriptionPrice = inscriptionPrice;
        this.maxParticipants = maxParticipants;
        this.numParticipants = numParticipants;
        this.place = place;
        this.trialTypeId = trialTypeId;
        this.provinceId = provinceId;
        this.avgScore = avgScore;
        this.hasScores = hasScores;
        this.showRegister = showRegister;
        this.showGiveNumber = showGiveNumber;
    }

    public boolean isShowRegister() {
        return showRegister;
    }

    public void setShowRegister(boolean showRegister) {
        this.showRegister = showRegister;
    }

    public boolean isShowGiveNumber() {
        return showGiveNumber;
    }

    public void setShowGiveNumber(boolean showGiveNumber) {
        this.showGiveNumber = showGiveNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrialName() {
        return trialName;
    }

    public void setTrialName(String trialName) {
        this.trialName = trialName;
    }

    public String getTrialDescription() {
        return trialDescription;
    }

    public void setTrialDescription(String trialDescription) {
        this.trialDescription = trialDescription;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getInscriptionPrice() {
        return inscriptionPrice;
    }

    public void setInscriptionPrice(BigDecimal inscriptionPrice) {
        this.inscriptionPrice = inscriptionPrice;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getTrialTypeId() {
        return trialTypeId;
    }

    public void setTrialTypeId(Long trialTypeId) {
        this.trialTypeId = trialTypeId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public boolean isHasScores() {
        return hasScores;
    }

    public void setHasScores(boolean hasScores) {
        this.hasScores = hasScores;
    }
}
