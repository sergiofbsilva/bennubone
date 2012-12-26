package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennubone.coffee.domain.error.CoffeeErrorCode;
import pt.ist.fenixframework.pstm.VersionNotAvailableException;

@Path("/orders")
public class OrderResource extends AbstractResource {

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("oid") String externalId) {
	try {
	    return Response.ok(loadJsonStringFromExternalId(externalId)).build();
	} catch (VersionNotAvailableException vnae) {
	    return errorResponse(CoffeeErrorCode.ORDER_NOT_FOUND);
	}
    }
}
