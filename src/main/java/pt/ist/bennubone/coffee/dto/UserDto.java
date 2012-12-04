package pt.ist.bennubone.coffee.dto;

public class UserDto extends DomainObjectDto {
    
    private String username;
    
    public UserDto() {}
    
    public UserDto withUsername(String username) {
        setUsername(username);
        return this;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
   
}