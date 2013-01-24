package pt.ist.bennubone.coffee.resource;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.core.security.Authenticate;
import pt.ist.bennubone.coffee.exception.BennuBoneException;
import pt.ist.bennubone.coffee.exception.CoffeeManagerError;

@Path("/login")
public class LoginResource extends AbstractResource {

	@Context
	HttpServletRequest request;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response login() throws URISyntaxException {
		String username = "ist155371";
		String password = "password";
		User authenticatedUser = Authenticate.login(request.getSession(true), username, password, false);
		if (authenticatedUser == null) {
			throw new BennuBoneException(CoffeeManagerError.UNAUTHORIZED);
		} else {
			return Response.ok().build();
		}
	}
}
