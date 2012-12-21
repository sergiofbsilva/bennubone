package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

import pt.ist.fenixframework.FenixFramework;

public class CoffeeManager extends CoffeeManager_Base {

	public CoffeeManager() {
		super();
		CoffeeItem ristretto = new CoffeeItem("Ristretto",new BigDecimal(0.375));
		addCoffeeItem(ristretto);
		CoffeeItem arpeggio = new CoffeeItem("Arpeggio", new BigDecimal(0.375));
		addCoffeeItem(arpeggio);
		CoffeeItem roma = new CoffeeItem("Roma", new BigDecimal(0.375));
		addCoffeeItem(roma);
		CoffeeItem livanto = new CoffeeItem("Livanto", new BigDecimal(0.375));
		addCoffeeItem(livanto);
		CoffeeItem volluto = new CoffeeItem("Volluto", new BigDecimal(0.375));
		addCoffeeItem(volluto);

		User david = new User("David", "Martinho", "redox", "davidmartinho@gmail.com");
		addUser(david);
		User sergio = new User("Sérgio", "Silva", "ashtray", "sergiofbsilva@gmail.com");
		addUser(sergio);
		User pedro = new User("Pedro", "Santos", "kirk", "pedro.san7os@gmail.com");
		addUser(pedro);

		CoffeeOrder davidOrder = new CoffeeOrder(david);
		davidOrder.addEntry(new CoffeeOrderEntry(ristretto, 2));
		davidOrder.addEntry(new CoffeeOrderEntry(arpeggio, 6));

		CoffeeOrder sergioOrder = new CoffeeOrder(sergio);
		sergioOrder.addEntry(new CoffeeOrderEntry(livanto, 5));
		sergioOrder.addEntry(new CoffeeOrderEntry(arpeggio, 4));

		CoffeeOrder pedroOrder = new CoffeeOrder(pedro);
		pedroOrder.addEntry(new CoffeeOrderEntry(livanto, 9));
		pedroOrder.addEntry(new CoffeeOrderEntry(roma, 3));

		CoffeeBatch orderBatch = new CoffeeBatch();
		orderBatch.addCoffeeOrder(davidOrder);
		orderBatch.addCoffeeOrder(sergioOrder);
		orderBatch.addCoffeeOrder(pedroOrder);

		addBatch(orderBatch);
	}

	public static CoffeeManager getInstance() {
		return FenixFramework.getRoot();
	}

}
