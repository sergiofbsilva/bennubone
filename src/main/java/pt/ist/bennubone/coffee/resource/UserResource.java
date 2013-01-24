package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ist.bennu.core.domain.groups.DynamicGroup;
import pt.ist.bennu.core.domain.groups.GroupException;
import pt.ist.bennu.core.domain.groups.PersistentGroup;
import pt.ist.bennubone.coffee.exception.BennuBoneException;
import pt.ist.bennubone.coffee.exception.CoffeeManagerError;

@Path("/users")
public class UserResource extends AbstractResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		getRequestAuthor();
		try {
			PersistentGroup coffeeUsersGroup = DynamicGroup.getInstance("COFFEE_USERS");
			return Response.ok(toJson("users", coffeeUsersGroup.getMembers())).build();
		} catch (GroupException e) {
			throw new BennuBoneException(CoffeeManagerError.COFFEE_USERS_GROUP_NOT_FOUND);
		}
	}

	@GET
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("oid") String externalId) {
		return Response.ok(loadJsonStringFromExternalId(externalId)).build();
	}

	// @POST
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// public Response create(@FormParam("username") final String username,
	// @FormParam("passwordHash") final String passwordHash,
	// @FormParam("salt") final String salt,
	// @FormParam("avatar") final String avatar) {
	// User user = createUser(username, passwordHash, salt, avatar);
	// return getUser(user.getExternalId());
	// }

	// @Service
	// private User createUser(final String username, final String passwordHash,
	// final String salt, final String avatar) {
	// User user = new User();
	// setUserSlots(username, passwordHash, salt, avatar, user);
	// return user;
	// }
	//
	// private void setUserSlots(final String username, final String
	// passwordHash,
	// final String salt, final String avatar, User user) {
	// user.setUsername(username);
	// user.setPasswordHash(passwordHash);
	// user.setAvatar(avatar);
	// user.setCoffeeManager(CoffeeManager.getInstance());
	// user.setSalt(salt);
	// }

	// @PUT
	// @Path("/{oid}")
	// @Service
	// public Response update(@PathParam("oid") String externalId,
	// @FormParam("username") final String username,
	// @FormParam("passwordHash") final String passwordHash,
	// @FormParam("salt") final String salt,
	// @FormParam("avatar") final String avatar) {
	// checkExternalId(externalId);
	// final User user = readDomainObject(externalId);
	// setUserSlots(username, passwordHash, salt, avatar, user);
	// return Response.ok().build();
	// }
	//
	// @DELETE
	// @Path("/{oid}")
	// @Service
	// public Response delete(@PathParam("oid") String externalId) {
	// checkExternalId(externalId);
	// User user = readDomainObject(externalId);
	// user.delete();
	// return Response.ok().build();
	// }
	//
	// private void checkExternalId(String externalId) {
	// if (StringUtils.isEmpty(externalId)) {
	// throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
	// }
	// }
}