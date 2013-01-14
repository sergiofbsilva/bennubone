package pt.ist.bennubone.coffee.resource;

import java.math.BigDecimal;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pt.ist.bennubone.coffee.domain.CoffeeItem;
import pt.ist.bennubone.coffee.domain.CoffeeItem.DuplicateCoffeeItemException;
import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.fenixWebFramework.services.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/items")
public class ItemResource extends AbstractResource {

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("oid") String oid) {
	return Response.ok(loadJsonStringFromExternalId(oid)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
	return Response.ok(toJson("items", CoffeeManager.getInstance().getCoffeeItemSet())).build();
    }

    @PUT
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setItem(@PathParam("oid") String oid, @FormParam("data") String jsonData) {
	final CoffeeItem item = readDomainObject(oid);
	final JsonParser parser = new JsonParser();
	final JsonObject obj = parser.parse(jsonData).getAsJsonObject();

	final String name = obj.get("name") != null ? obj.get("name").getAsString() : null;

	final String imageUrl = obj.get("imageUrl") != null ? obj.get("imageUrl").getAsString() : null;
	final BigDecimal unitValue = obj.get("unitValue") != null ? obj.get("unitValue").getAsBigDecimal() : null;
	setItem(item, name, imageUrl, unitValue);
	return Response.ok(loadJsonStringFor(item)).build();
    }

    @Service
    public void setItem(CoffeeItem item, final String name, final String imageUrl, final BigDecimal unitValue) {
	if (imageUrl != null) {
	    item.setImageUrl(imageUrl);
	}
	if (name != null) {
	    item.setName(name);
	}

	if (unitValue != null) {
	    item.setUnitValue(unitValue);
	}
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    // { 'name' : 'Volluto', 'imageUrl' : 'http://www.google.pt/image.gif',
    // 'unitValue':'3.75', 'units' : '10' }
    public Response addItem(@FormParam("model") String jsonData) {
	try {
	    final CoffeeItem coffeeItem = createFromJson(jsonData, CoffeeItem.class);
	    return Response.ok(loadJsonStringFor(coffeeItem)).build();
	} catch (DuplicateCoffeeItemException dcie) {
	    throw new WebApplicationException(Response.status(Status.PRECONDITION_FAILED).entity(dcie).build());
	}

    }

    @DELETE
    @Service
    @Path("/{oid}")
    public Response deleteItem(@PathParam("oid") String oid) {
	CoffeeItem item = readDomainObject(oid);
	item.delete();
	return Response.ok().build();
    }
}
