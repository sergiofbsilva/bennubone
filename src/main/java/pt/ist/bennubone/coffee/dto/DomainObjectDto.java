package pt.ist.bennubone.coffee.dto;

public abstract class DomainObjectDto {
    
    private String externalId;
    
    public DomainObjectDto() {}
    
    public DomainObjectDto withExternalId(String externalId) {
        setExternalId(externalId);
        return this;
    }
    
    public String getExternalId() {
        return this.externalId;
    }
    
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
    
}