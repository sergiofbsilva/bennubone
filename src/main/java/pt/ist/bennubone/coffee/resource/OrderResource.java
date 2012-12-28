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

import org.apache.commons.lang.StringUtils;

import pt.ist.bennubone.coffee.domain.CoffeeItem;
import pt.ist.bennubone.coffee.domain.CoffeeOrder;
import pt.ist.bennubone.coffee.domain.CoffeeOrderEntry;
import pt.ist.bennubone.coffee.domain.User;
import pt.ist.bennubone.coffee.domain.error.CoffeeErrorCode;
import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.pstm.VersionNotAvailableException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(@FormParam("data") String jsonData) {
	CoffeeOrder coffeeOrder = createFromJson(jsonData, CoffeeOrder.class);
	return Response.ok(loadJsonStringFor(coffeeOrder)).build();
    }

    @PUT
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putOrder(@PathParam("oid") String externalId, @FormParam("data") String jsonData) {
	final CoffeeOrder coffeeOrder = editOrder(externalId, jsonData);
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
    private CoffeeOrder editOrder(String externalId, String jsonData) {
	final CoffeeOrder order = readDomainObject(externalId);
	final JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
	final String userId = jsonObject.get("userId").getAsString();
	if (!StringUtils.isEmpty(userId)) {
	    final User user = (User) User.fromExternalId(userId);
	    if (!user.equals(order.getUser())) {
		order.setUser(user);
	    }
	    for (CoffeeOrderEntry orderEntry : order.getEntry()) {
		orderEntry.delete();
	    }
	    final JsonArray entries = jsonObject.get("entries").getAsJsonArray();
	    for (JsonElement entry : entries) {
		final JsonObject entryJsonObject = entry.getAsJsonObject();
		final String itemId = entryJsonObject.get("itemId").getAsString();
		final CoffeeItem item = readDomainObject(itemId);
		order.addEntry(item, entryJsonObject.get("quantity").getAsInt());
	    }
	}
	return order;
    }
}
