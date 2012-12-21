package pt.ist.bennubone.coffee.domain.error;

import javax.ws.rs.core.Response;

public enum CoffeeErrorCode {

	USER_NOT_FOUND(Response.Status.NOT_FOUND, 1000, "User not found"),
	ORDER_NOT_FOUND(Response.Status.NOT_FOUND,1001, "Order not found"),
	ORDER_BATCH_NOT_FOUND(Response.Status.NOT_FOUND, 1002, "Order batch not found");
	
	private Response.Status responseStatusCode;
	private int errorCode;
	private String message;
	
	private CoffeeErrorCode(Response.Status responseStatusCode, int errorCode, String message) {
		this.responseStatusCode = responseStatusCode;
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public Response.Status getResponseStatusCode() {
		return responseStatusCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getMessage() {
		return message;
	}
	
}
