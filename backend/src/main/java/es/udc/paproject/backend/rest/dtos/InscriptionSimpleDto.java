package es.udc.paproject.backend.rest.dtos;

public class InscriptionSimpleDto {
    private Long id;

    private int number;

    public InscriptionSimpleDto(Long id, int number){
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
