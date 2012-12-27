package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.FenixFramework;

public class CoffeeManager extends CoffeeManager_Base {

    public CoffeeManager() {
	super();
    }

    public static CoffeeManager getInstance() {
	return FenixFramework.getRoot();
    }

    public CoffeeItem getCoffeeItem(final String name) {
	for (final CoffeeItem item : getCoffeeItemSet()) {
	    if (item.getName().equalsIgnoreCase(name.trim())) {
		return item;
	    }
	}
	return null;
    }

    public boolean hasCoffeeItem(final String name) {
	return getCoffeeItem(name) != null;
    }

    @Service
    public Boolean initDB() {
	if (!hasAnyCoffeeItem()) {
	    CoffeeItem ristretto = new CoffeeItem("Ristretto", new BigDecimal(0.375));
	    CoffeeItem arpeggio = new CoffeeItem("Arpeggio", new BigDecimal(0.375));
	    CoffeeItem roma = new CoffeeItem("Roma", new BigDecimal(0.375));
	    CoffeeItem livanto = new CoffeeItem("Livanto", new BigDecimal(0.375));
	    new CoffeeItem("Volluto", new BigDecimal(0.375));

	    User david = new User("David", "Martinho", "redox", "davidmartinho@gmail.com");
	    User sergio = new User("Sérgio", "Silva", "ashtray", "sergiofbsilva@gmail.com");
	    User pedro = new User("Pedro", "Santos", "kirk", "pedro.san7os@gmail.com");

	    CoffeeOrder davidOrder = new CoffeeOrder(david);
	    davidOrder.addEntry(ristretto, 2);
	    davidOrder.addEntry(arpeggio, 6);

	    CoffeeOrder sergioOrder = new CoffeeOrder(sergio);
	    sergioOrder.addEntry(livanto, 5);
	    sergioOrder.addEntry(arpeggio, 4);

	    CoffeeOrder pedroOrder = new CoffeeOrder(pedro);
	    pedroOrder.addEntry(livanto, 9);
	    pedroOrder.addEntry(roma, 3);

	    new CoffeeBatch(davidOrder, sergioOrder, pedroOrder);
	    return true;
	}
	return false;
    }

}
