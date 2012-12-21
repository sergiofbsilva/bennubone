package pt.ist.bennubone.coffee.domain.exception;

public class CoffeeManagerException extends Exception {

	private static final long serialVersionUID = 1L;

	private int errorCode;
	private Throwable throwable;

	public CoffeeManagerException(int errorCode, String message) {
		this(errorCode, message, null);
	}
	
	public CoffeeManagerException(int errorCode, String message, Throwable throwable) {
		super(message);
		this.errorCode = errorCode;
		this.throwable = throwable;
	}
	
	public Throwable getThrowable() {
		return throwable;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
}
