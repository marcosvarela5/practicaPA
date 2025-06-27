package es.udc.paproject.backend.rest.dtos;

public class ProvinceDto {
    private Long id;
    private String provinceName;

    public ProvinceDto() {
    }

    public ProvinceDto(Long id, String provinceName) {
        this.id = id;
        this.provinceName = provinceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

}
