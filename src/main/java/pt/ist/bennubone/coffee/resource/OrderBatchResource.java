package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OrderBatchResource extends AbstractResource {

	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderBatch(@PathParam("oid") String externalId) {
		return Response.ok(loadJsonStringFromExternalId(externalId)).build();
	}
	
}
