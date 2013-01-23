package pt.ist.bennubone.coffee.resource;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennubone.coffee.domain.Role;

@Path("roles")
public class RoleResource extends AbstractResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRoles() {
		Set<Role> roleSet = getCoffeeManager().getRoleSet();
		return Response.ok().entity(toJson("roles", roleSet)).build();
	}

}
