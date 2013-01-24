package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

@Path("/settings")
public class ApplicationSettingsResource extends AbstractResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSettings() {
		JsonObject jsonObject = new JsonObject();
		if (isCasEnabled()) {
			jsonObject.addProperty("casEnabled", true);
			jsonObject.addProperty("loginUrl", getCasConfig().getCasLoginUrl());
			jsonObject.addProperty("logoutUrl", getCasConfig().getCasLogoutUrl());
		} else {
			jsonObject.addProperty("casEnabled", false);
		}
		return Response.ok().entity(jsonObject.toString()).build();
	}

}
