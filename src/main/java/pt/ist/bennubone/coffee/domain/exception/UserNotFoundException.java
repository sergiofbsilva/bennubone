package pt.ist.bennubone.coffee.domain.exception;

public class UserNotFoundException extends CoffeeManagerException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Throwable throwable) {
		super(1001, "User not found", throwable);
	}
	
}
