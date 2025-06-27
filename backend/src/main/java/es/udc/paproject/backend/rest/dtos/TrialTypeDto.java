package es.udc.paproject.backend.rest.dtos;

public class TrialTypeDto {

    private Long id;
    private String trialTypeName;

    public TrialTypeDto() {
    }

    public TrialTypeDto(Long id, String trialTypeName) {
        this.id = id;
        this.trialTypeName = trialTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrialTypeName() {
        return trialTypeName;
    }

    public void setTrialTypeName(String trialTypeName) {
        this.trialTypeName = trialTypeName;
    }
}

