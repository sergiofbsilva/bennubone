package pt.ist.bennubone.coffee.exception;

import javax.ws.rs.core.Response.Status;

public enum CoffeeManagerError {

    NOT_AUTHORIZED(Status.ACCEPTED, 1000, "You do not have authorization."), DOMAIN_OBJECT_NOT_FOUND(Status.ACCEPTED, 1001,
	    "Domain object not found.");

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
