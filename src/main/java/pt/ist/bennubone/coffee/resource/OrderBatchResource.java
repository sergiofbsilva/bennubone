package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennubone.coffee.domain.error.CoffeeErrorCode;
import pt.ist.fenixframework.pstm.VersionNotAvailableException;

@Path("batch")
public class OrderBatchResource extends AbstractResource {

	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderBatch(@PathParam("oid") String externalId) {
		try {
			String responseString = loadJsonStringFromExternalId(externalId);
			return Response.ok(responseString).build();
		} catch (VersionNotAvailableException vnae) {
			return errorResponse(CoffeeErrorCode.ORDER_BATCH_NOT_FOUND);
		}
	}
	
}
