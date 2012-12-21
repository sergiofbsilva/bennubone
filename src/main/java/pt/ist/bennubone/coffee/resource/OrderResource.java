package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennubone.coffee.domain.exception.OrderNotFoundException;
import pt.ist.fenixframework.pstm.VersionNotAvailableException;

@Path("/order")
public class OrderResource extends AbstractResource {

	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrder(@PathParam("oid") String externalId) {
		try {
			String responseString = loadJsonStringFromExternalId(externalId);
			return Response.ok(responseString).build();
		} catch (VersionNotAvailableException vnae) {
			OrderNotFoundException e = new OrderNotFoundException(vnae);
			return Response.status(Response.Status.NOT_FOUND).entity(loadJsonStringFor(e)).build();
		}
	}
}
