package pt.ist.bennubone.coffee.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BennuBoneException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	private final CoffeeManagerError error;

	public BennuBoneException(CoffeeManagerError error) {
		this.error = error;
	}

	public Response.Status getResponseStatus() {
		return error.getResponseStatusCode();
	}

	public int getInternalErrorCode() {
		return error.getInternalErrorCode();
	}

	@Override
	public String getMessage() {
		return error.getMessage();
	}

}
