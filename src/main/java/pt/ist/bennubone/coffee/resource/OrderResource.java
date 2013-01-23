package pt.ist.bennubone.coffee.resource;

import java.util.Iterator;

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

import pt.ist.bennubone.coffee.domain.CoffeeItem;
import pt.ist.bennubone.coffee.domain.CoffeeOrder;
import pt.ist.bennubone.coffee.domain.CoffeeOrderEntry;
import pt.ist.bennubone.coffee.domain.User;
import pt.ist.fenixWebFramework.services.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/orders")
public class OrderResource extends AbstractResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrders() {
		return Response.ok(toJson("orders", getRequestAuthor().getCoffeeOrderSet())).build();
	}

	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrder(@PathParam("oid") String externalId) {
		return Response.ok(loadJsonStringFromExternalId(externalId)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOrder(@FormParam("model") String jsonData) {
		User author = getRequestAuthor();
		CoffeeOrder coffeeOrder = createOrder(author, jsonData);
		// CoffeeOrder coffeeOrder = createFromJson(jsonData, CoffeeOrder.class);
		return Response.ok(loadJsonStringFor(coffeeOrder)).build();
	}

	@PUT
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response putOrder(@PathParam("oid") String externalId, @FormParam("model") String jsonData) {
		User author = getRequestAuthor();
		System.out.println(jsonData);
		final CoffeeOrder coffeeOrder = editOrder(author, externalId, jsonData);
		return Response.ok(loadJsonStringFor(coffeeOrder)).build();
	}

	@DELETE
	@Path("/{oid}")
	@Service
	public Response deleteOrder(@PathParam("oid") String externalId) {
		CoffeeOrder coffeeOrder = readDomainObject(externalId);
		coffeeOrder.delete();
		return Response.ok(loadJsonStringFor(coffeeOrder)).build();
	}

	@Service
	private CoffeeOrder createOrder(User author, String jsonData) {
		CoffeeOrder coffeeOrder = new CoffeeOrder(author);
		System.out.println(jsonData);
		JsonArray entries = new JsonParser().parse(jsonData).getAsJsonObject().get("entries").getAsJsonArray();
		Iterator<JsonElement> iterator = entries.iterator();
		while (iterator.hasNext()) {
			JsonObject orderEntry = iterator.next().getAsJsonObject();
			String externalId = orderEntry.get("id").getAsString();
			int quantity = orderEntry.get("quantity").getAsInt();
			CoffeeItem item = CoffeeItem.fromExternalId(externalId);
			coffeeOrder.addEntry(new CoffeeOrderEntry(item, quantity));
		}
		return coffeeOrder;
	}

	@Service
	private CoffeeOrder editOrder(User author, String externalId, String jsonData) {
		final CoffeeOrder order = readDomainObject(externalId);
		for (CoffeeOrderEntry entry : order.getEntrySet()) {
			entry.delete();
		}
		final JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
		final JsonArray entries = jsonObject.get("entries").getAsJsonArray();
		Iterator<JsonElement> iterator = entries.iterator();
		while (iterator.hasNext()) {
			JsonObject entry = iterator.next().getAsJsonObject();
			String itemExternalId = entry.get("id").getAsString();
			CoffeeItem item = CoffeeItem.fromExternalId(itemExternalId);
			int quantity = entry.get("quantity").getAsInt();
			order.addEntry(item, quantity);
		}
		return order;
	}
}
