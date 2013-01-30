package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pt.ist.bennubone.coffee.domain.CoffeeManager;

@Path("/initDB")
public class InitBD {

	@GET
	public Response doIt() {
		if (CoffeeManager.getInstance().initDB()) {
			return Response.ok("DB init sucessfully.").build();
		} else {
			return Response.ok("DB already initialized.").build();
		}
	}
}
