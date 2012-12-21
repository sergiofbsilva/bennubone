package pt.ist.bennubone.coffee.domain;

public class CoffeeOrder extends CoffeeOrder_Base {
    
    public CoffeeOrder(User author) {
    	setUser(author);
    }
}
