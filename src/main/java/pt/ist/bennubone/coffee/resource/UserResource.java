package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import pt.ist.bennubone.coffee.dto.UserDto;
import pt.ist.bennubone.coffee.dto.mapper.DtoMapper;
import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.domain.User;

@Path("/user")
public class UserResource extends AbstractResource {
    
    @GET
	@Path("{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getUserByExternId(@PathParam("externalId") String externalId) {
        return DtoMapper.userDtoFromUser((User)CoffeeManager.fromExternalId(externalId));
    }
    
    
}