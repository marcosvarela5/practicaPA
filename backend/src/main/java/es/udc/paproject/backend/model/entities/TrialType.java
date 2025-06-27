package es.udc.paproject.backend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrialType {

    private String trialTypeName;

    private Long id;

    public TrialType(){}

    public TrialType(String trialTypeName){
        this.trialTypeName = trialTypeName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getTrialTypeName() {
        return trialTypeName;
    }

    public void setTrialTypeName(String name) {
        this.trialTypeName = name;
    }
}
