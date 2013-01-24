package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pt.ist.bennu.core.domain.User;

@Path("profile")
public class ProfileResource extends AbstractResource {

	@GET
	public Response getProfile() {
		User author = getRequestAuthor();
		return Response.ok().entity(loadJsonStringFor(author)).build();
	}

}
