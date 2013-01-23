package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

import pt.ist.bennubone.coffee.util.CoffeeManagerUtils;
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
			CoffeeItem ristretto = new CoffeeItem("Ristretto", new BigDecimal("0.375"),
					"http://nesclub.nespresso.com/img/pictures/00746.gif");
			CoffeeItem arpeggio = new CoffeeItem("Arpeggio", new BigDecimal("0.375"),
					"http://nesclub.nespresso.com/img/pictures/00673.gif");
			CoffeeItem roma = new CoffeeItem("Roma", new BigDecimal("0.375"),
					"http://nesclub.nespresso.com/img/pictures/00747.gif");
			new CoffeeItem("Decaffeinato Intenso", new BigDecimal("0.375"), "http://nesclub.nespresso.com/img/pictures/00707.gif");
			CoffeeItem livanto = new CoffeeItem("Livanto", new BigDecimal("0.375"),
					"http://nesclub.nespresso.com/img/pictures/00712.gif");
			new CoffeeItem("Capriccio", new BigDecimal("0.375"), "http://nesclub.nespresso.com/img/pictures/00674.gif");
			new CoffeeItem("Volluto", new BigDecimal("0.375"), "http://nesclub.nespresso.com/img/pictures/00751.gif");
			new CoffeeItem("Cosi", new BigDecimal("0.375"), "http://nesclub.nespresso.com/img/pictures/00676.gif");
			new CoffeeItem("Decaffeinato", new BigDecimal("0.375"), "http://nesclub.nespresso.com/img/pictures/00706.gif");
			new CoffeeItem("Fortissio Lungo", new BigDecimal("0.395"), "http://nesclub.nespresso.com/img/pictures/02455.jpg");
			new CoffeeItem("Vivalto", new BigDecimal("0.395"), "http://nesclub.nespresso.com/img/pictures/00750.gif");
			new CoffeeItem("Decaffeinato Lungo", new BigDecimal("0.395"), "http://nesclub.nespresso.com/img/pictures/00708.gif");
			new CoffeeItem("Finezzo", new BigDecimal("0.395"), "http://nesclub.nespresso.com/img/pictures/00709.gif");
			new CoffeeItem("Kit descalcificação", new BigDecimal("10"), 2, "http://nesclub.nespresso.com/img/pictures/01881.jpg");
			new CoffeeItem("Dhjana", new BigDecimal("0.39"), "http://nesclub.nespresso.com/img/pictures/05441.jpg");
			new CoffeeItem("Variations Chocolate Negro", new BigDecimal("0.42"),
					"http://nesclub.nespresso.com/img/pictures/05836.jpg");
			new CoffeeItem("Rosabaya from Colombia", new BigDecimal("0.415"),
					"http://nesclub.nespresso.com/img/pictures/02460.jpg");
			new CoffeeItem("Indriya from India", new BigDecimal("0.415"), "http://nesclub.nespresso.com/img/pictures/02466.jpg");
			new CoffeeItem("Dulso do Brasil", new BigDecimal("0.415"), "http://nesclub.nespresso.com/img/pictures/02463.jpg");
			new CoffeeItem("Variation Caramelo", new BigDecimal("0.38"), "http://nesclub.nespresso.com/img/pictures/04155.jpg");
			new CoffeeItem("Variations Flor de Baunilha", new BigDecimal("0.42"),
					"http://nesclub.nespresso.com/img/pictures/05837.jpg");
			new CoffeeItem("Naora Limited Edition", new BigDecimal("0.425"),
					"http://nesclub.nespresso.com/img/pictures/06621.jpg");
			new CoffeeItem("Variations Cereja", new BigDecimal("0.42"), "http://nesclub.nespresso.com/img/pictures/05836.jpg");

			Role administratorRole = new Role("Administrator");
			Role coffeeConsumerRole = new Role("Coffee Consumer");
			Role coffeeBatchManager = new Role("Coffee Batch Manager");

			User david = new User("David Martinho", "davidmartinho@gmail.com", "pass");
			david.addRole(coffeeConsumerRole);
			david.addRole(administratorRole);
			User sergio = new User("Sérgio Silva", "sergiofbsilva@gmail.com", "pass");
			sergio.addRole(coffeeConsumerRole);
			User pedro = new User("Pedro Santos", "pedro.san7os@gmail.com", "pass");
			pedro.addRole(coffeeConsumerRole);
			User susana = new User("Susana Fernandes", "susanal@gmail.com", "pass");
			susana.addRole(coffeeConsumerRole);
			susana.addRole(coffeeBatchManager);

			// CoffeeOrder davidOrder0 = new CoffeeOrder(david);
			// davidOrder0.addEntry(ristretto, 5);
			// davidOrder0.addEntry(livanto, 15);
			//
			// CoffeeOrder davidOrder = new CoffeeOrder(david);
			// davidOrder.addEntry(ristretto, 2);
			// davidOrder.addEntry(arpeggio, 6);
			//
			// CoffeeOrder davidOrder2 = new CoffeeOrder(david);
			// davidOrder2.addEntry(ristretto, 7);
			// davidOrder2.addEntry(arpeggio, 10);
			//
			// CoffeeOrder sergioOrder = new CoffeeOrder(sergio);
			// sergioOrder.addEntry(livanto, 5);
			// sergioOrder.addEntry(arpeggio, 4);
			//
			// CoffeeOrder pedroOrder = new CoffeeOrder(pedro);
			// pedroOrder.addEntry(livanto, 9);
			// pedroOrder.addEntry(roma, 3);
			//
			// new CoffeeBatch(davidOrder0);
			//
			// CoffeeBatch batch1 = new CoffeeBatch(davidOrder);
			// batch1.send();
			//
			// CoffeeBatch batch2 = new CoffeeBatch(davidOrder2, sergioOrder, pedroOrder);
			// batch2.send();
			// batch2.receive();

			return true;
		}
		return false;
	}

	public User login(String email, String password) {
		for (User user : getUserSet()) {
			if (user.getEmail().equals(email)) {
				String passwordHash = CoffeeManagerUtils.calculatePasswordHash(password, user.getSalt());
				if (passwordHash.equals(user.getPasswordHash())) {
					return user;
				} else {
					return null;
				}
			}
		}
		return null;
	}
}
