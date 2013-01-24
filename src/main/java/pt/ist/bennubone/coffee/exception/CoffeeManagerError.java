package pt.ist.bennubone.coffee.exception;

import javax.ws.rs.core.Response.Status;

public enum CoffeeManagerError {

	UNAUTHORIZED(Status.UNAUTHORIZED, 1000, "You must authenticate first"), DOMAIN_OBJECT_NOT_FOUND(Status.ACCEPTED, 1001,
			"Domain object not found."), COFFEE_USERS_GROUP_NOT_FOUND(Status.INTERNAL_SERVER_ERROR, 1002,
			"Could not load coffee manager users");

	private Status status;
	private int internalErrorCode;
	private String message;

	private CoffeeManagerError(Status status, int internalErrorCode, String message) {
		this.status = status;
		this.internalErrorCode = internalErrorCode;
		this.message = message;
	}

	public Status getResponseStatusCode() {
		return status;
	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public String getMessage() {
		return message;
	}

}
