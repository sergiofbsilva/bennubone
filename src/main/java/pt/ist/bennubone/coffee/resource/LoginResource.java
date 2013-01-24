package pt.ist.bennubone.coffee.resource;

import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.core.security.UserView;
import pt.ist.bennubone.coffee.exception.BennuBoneException;
import pt.ist.bennubone.coffee.exception.CoffeeManagerError;

@Path("/login")
public class LoginResource extends AbstractResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLoginStatus() {
		if (UserView.hasUser()) {
			return Response.ok().entity(loadJsonStringFor(UserView.getUser())).build();
		} else {
			throw new BennuBoneException(CoffeeManagerError.UNAUTHORIZED);
		}
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response login(@FormParam("username") String username, @FormParam("password") String password)
			throws URISyntaxException {
		if (isCasEnabled()) {
			User authenticatedUser = login(username, password, false);
			if (authenticatedUser == null) {
				throw new BennuBoneException(CoffeeManagerError.UNAUTHORIZED);
			} else {
				return Response.ok().build();
			}
		} else {
			throw new BennuBoneException(CoffeeManagerError.UNAUTHORIZED);
		}

	}
}
