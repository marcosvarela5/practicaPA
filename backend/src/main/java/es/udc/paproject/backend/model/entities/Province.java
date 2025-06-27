package es.udc.paproject.backend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Province{

    @Id
    private Long id;
    private String provinceName;

    public Province(){

    }
    public Province(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setProvinceName(final String provinceName) {
        this.provinceName = provinceName;
    }
}