package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class TrialSimpleDto {
    private Long id;
    private String trialName;
    private Long trialTypeId;
    private float avgScore;
    private Long provinceId;
    private long dateTime;
    private boolean hasScores;


    public TrialSimpleDto() {
    }

    public TrialSimpleDto(Long id, String trialName, Long dateTime, Long trialTypeId,
                          Long provinceId, float avgScore, boolean hasScores) {
        this.id = id;
        this.trialName = trialName;
        this.dateTime = dateTime;
        this.trialTypeId = trialTypeId;
        this.provinceId = provinceId;
        this.avgScore = avgScore;
        this.hasScores = hasScores;
    }

    public boolean isHasScores() {
        return hasScores;
    }

    public void setHasScores(boolean hasScores) {
        this.hasScores = hasScores;
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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
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
}
