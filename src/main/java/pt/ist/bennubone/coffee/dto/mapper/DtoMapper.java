package pt.ist.bennubone.coffee.dto.mapper;

import pt.ist.bennubone.coffee.domain.User;
import pt.ist.bennubone.coffee.dto.UserDto;

public class DtoMapper {
    
    public static UserDto userDtoFromUser(User user) {
        return new UserDto().withUsername(user.getUsername());
    }
    
}