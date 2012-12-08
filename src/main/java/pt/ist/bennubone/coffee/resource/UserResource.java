package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.Consumes;
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

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.domain.User;
import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.pstm.AbstractDomainObject;

@Path("/user")
public class UserResource extends AbstractResource {

    // private static final GsonBuilder builder = new
    // GsonBuilder().registerTypeAdapter(User.class, new TypeAdapter<User>() {
    //
    // @Override
    // public void write(JsonWriter out, User value) throws IOException {
    // out.beginObject();
    // out.name("username").value(value.getUsername()).name("avatar").value(value.getAvatar()).close();
    // out.endObject();
    // }
    //
    // @Override
    // public User read(JsonReader in) throws IOException {
    // return null;
    // }
    //
    // });

    private <T extends DomainObject> T readDomainObject(String externalId) {
	T obj = AbstractDomainObject.fromExternalId(externalId);
	if (obj == null) {
	    throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
	}
	return obj;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(readAll()).build();
    }

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("oid") String externalId) {
	if ("0".equals(externalId)) {
	    return Response.ok(readAll()).build();
	}
	User user = readDomainObject(externalId);
	// Gson gson = builder.create();
	return Response.ok(user.json().toJSONString()).build();
    }

    private String readAll() {
	JSONArray users = new JSONArray();
	for (User user : CoffeeManager.getInstance().getUserSet()) {
	    users.add(user.json());
	}
	return users.toJSONString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("username") final String username, @FormParam("passwordHash") final String passwordHash,
	    @FormParam("salt") final String salt, @FormParam("avatar") final String avatar) {
	User user = createUser(username, passwordHash, salt, avatar);
	return read(user.getExternalId());
    }

    @Service
    private User createUser(final String username, final String passwordHash, final String salt, final String avatar) {
	User user = new User();
	setUserSlots(username, passwordHash, salt, avatar, user);
	return user;
    }

    private void setUserSlots(final String username, final String passwordHash, final String salt, final String avatar, User user) {
	user.setUsername(username);
	user.setPasswordHash(passwordHash);
	user.setAvatar(avatar);
	user.setCoffeeManager(CoffeeManager.getInstance());
	user.setSalt(salt);
    }

    @PUT
    @Path("/{oid}")
    @Service
    public Response update(@PathParam("oid") String externalId, @FormParam("username") final String username,
	    @FormParam("passwordHash") final String passwordHash, @FormParam("salt") final String salt,
	    @FormParam("avatar") final String avatar) {
	checkExternalId(externalId);
	final User user = readDomainObject(externalId);
	setUserSlots(username, passwordHash, salt, avatar, user);
	return Response.ok().build();
    }

    @DELETE
    @Path("/{oid}")
    @Service
    public Response delete(@PathParam("oid") String externalId) {
	checkExternalId(externalId);
	User user = readDomainObject(externalId);
	user.delete();
	return Response.ok().build();
    }

    private void checkExternalId(String externalId) {
	if (StringUtils.isEmpty(externalId)) {
	    throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
	}
    }
}