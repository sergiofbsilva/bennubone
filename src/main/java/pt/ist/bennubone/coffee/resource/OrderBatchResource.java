package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennu.service.Service;
import pt.ist.bennubone.coffee.domain.CoffeeBatch;
import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.domain.CoffeeOrder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/batches")
public class OrderBatchResource extends AbstractResource {

	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderBatch(@PathParam("oid") String externalId) {
		String responseString = loadJsonStringFromExternalId(externalId);
		return Response.ok(responseString).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBatches() {
		return Response.ok(toJson("batches", CoffeeManager.getInstance().getBatch())).build();
	}

	// {orders : [oid1,oid2,...,oidN] }
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response newBatch(@FormParam("data") String data) {
		return Response.ok(createFromJson(data, CoffeeBatch.class)).build();
	}

	@PUT
	@Path("/{oid}")
	public Response editBatch(@PathParam("oid") String externalId, @FormParam("data") String data) {
		final CoffeeBatch coffeeBatch = readDomainObject(externalId);
		return Response.ok(loadJsonStringFor(editBatch(coffeeBatch, data))).build();
	}

	@Service
	private CoffeeBatch editBatch(CoffeeBatch coffeeBatch, String data) {
		final JsonParser parser = new JsonParser();
		final JsonObject jsonObject = parser.parse(data).getAsJsonObject();
		final JsonArray orders = jsonObject.get("orders").getAsJsonArray();
		for (final JsonElement order : orders) {
			CoffeeOrder coffeeOrder = readDomainObject(order.getAsString());
			coffeeBatch.addCoffeeOrder(coffeeOrder);
		}
		return coffeeBatch;
	}

	@DELETE
	@Path("/{oid}")
	@Service
	public Response delete(@PathParam("oid") String externalId) {
		CoffeeBatch batch = readDomainObject(externalId);
		batch.delete();
		return Response.ok().build();
	}

	@DELETE
	@Path("/{oid}/{orderOid}")
	@Service
	public Response delete(@PathParam("oid") String externalId, @PathParam("orderOid") String orderOid) {
		CoffeeBatch batch = readDomainObject(externalId);
		CoffeeOrder coffeeOrder = readDomainObject(orderOid);
		batch.removeCoffeeOrder(coffeeOrder);
		return Response.ok().build();
	}

}
