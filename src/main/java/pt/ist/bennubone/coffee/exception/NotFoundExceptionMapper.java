package pt.ist.bennubone.coffee.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.JsonObject;
import com.sun.jersey.api.NotFoundException;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException e) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("internalErrorCode", -1);
		jsonObject.addProperty("message", "Resource not found");
		return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).entity(jsonObject.toString()).build();
	}
}
