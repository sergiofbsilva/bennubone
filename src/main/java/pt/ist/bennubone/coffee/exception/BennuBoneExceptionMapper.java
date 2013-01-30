package pt.ist.bennubone.coffee.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.JsonObject;

@Provider
public class BennuBoneExceptionMapper implements ExceptionMapper<BennuBoneException> {

	@Override
	public Response toResponse(BennuBoneException exception) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("internalErrorCode", exception.getInternalErrorCode());
		jsonObject.addProperty("message", exception.getMessage());
		return Response.status(exception.getResponseStatus()).entity(jsonObject.toString()).type(MediaType.APPLICATION_JSON)
				.build();
	}

}
