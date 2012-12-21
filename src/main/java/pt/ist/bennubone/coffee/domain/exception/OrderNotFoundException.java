package pt.ist.bennubone.coffee.domain.exception;

public class OrderNotFoundException extends CoffeeManagerException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Throwable throwable) {
		super(1000, "Order not found", throwable);
	}
	
}
