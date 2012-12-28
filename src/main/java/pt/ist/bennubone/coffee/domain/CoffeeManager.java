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
	    new CoffeeItem("Ristretto", "http://nesclub.nespresso.com/img/pictures/00746.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Arpeggio", "http://nesclub.nespresso.com/img/pictures/00673.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Roma", "http://nesclub.nespresso.com/img/pictures/00747.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Decaffeinato Intenso", "http://nesclub.nespresso.com/img/pictures/00707.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Livanto", "http://nesclub.nespresso.com/img/pictures/00712.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Capriccio", "http://nesclub.nespresso.com/img/pictures/00674.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Volluto", "http://nesclub.nespresso.com/img/pictures/00751.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Cosi", "http://nesclub.nespresso.com/img/pictures/00676.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Decaffeinato", "http://nesclub.nespresso.com/img/pictures/00706.gif", new BigDecimal("0.375"));
	    new CoffeeItem("Fortissio Lungo", "http://nesclub.nespresso.com/img/pictures/02455.jpg", new BigDecimal("0.395"));
	    new CoffeeItem("Vivalto", "http://nesclub.nespresso.com/img/pictures/00750.gif", new BigDecimal("0.395"));
	    new CoffeeItem("Decaffeinato Lungo", "http://nesclub.nespresso.com/img/pictures/00708.gif", new BigDecimal("0.395"));
	    new CoffeeItem("Finezzo", "http://nesclub.nespresso.com/img/pictures/00709.gif", new BigDecimal("0.395"));
	    new CoffeeItem("Kit descalcificação Essenza / Le Cube / Latissima /Citiz (2 unidades)",
		    "http://nesclub.nespresso.com/img/pictures/01881.jpg", new BigDecimal("10"));
	    new CoffeeItem("Dhjana", "http://nesclub.nespresso.com/img/pictures/05441.jpg", new BigDecimal("0.39"));
	    new CoffeeItem("Variations Chocolate Negro", "http://nesclub.nespresso.com/img/pictures/05836.jpg", new BigDecimal(
		    "0.42"));
	    new CoffeeItem("Rosabaya from Colombia", "http://nesclub.nespresso.com/img/pictures/02460.jpg", new BigDecimal(
		    "0.415"));
	    new CoffeeItem("Indriya from India", "http://nesclub.nespresso.com/img/pictures/02466.jpg", new BigDecimal("0.415"));
	    new CoffeeItem("Dulso do Brasil", "http://nesclub.nespresso.com/img/pictures/02463.jpg", new BigDecimal("0.415"));
	    new CoffeeItem("Variation Caramelo", "http://nesclub.nespresso.com/img/pictures/04155.jpg", new BigDecimal("0.38"));
	    new CoffeeItem("Variations Flor de Baunilha", "http://nesclub.nespresso.com/img/pictures/05837.jpg", new BigDecimal(
		    "0.42"));
	    new CoffeeItem("Naora Limited Edition", "http://nesclub.nespresso.com/img/pictures/06621.jpg",
		    new BigDecimal("0.425"));
	    new CoffeeItem("Variations Cereja", "http://nesclub.nespresso.com/img/pictures/05836.jpg", new BigDecimal("0.42"));

	    User david = new User("David Martinho", "davidmartinho@gmail.com", "pass");
	    User sergio = new User("Sérgio Silva", "sergiofbsilva@gmail.com", "pass");
	    User pedro = new User("Pedro Santos", "pedro.san7os@gmail.com", "pass");

	    // CoffeeOrder davidOrder = new CoffeeOrder(david);
	    // davidOrder.addEntry(ristretto, 2);
	    // davidOrder.addEntry(arpeggio, 6);
	    //
	    // CoffeeOrder sergioOrder = new CoffeeOrder(sergio);
	    // sergioOrder.addEntry(livanto, 5);
	    // sergioOrder.addEntry(arpeggio, 4);
	    //
	    // CoffeeOrder pedroOrder = new CoffeeOrder(pedro);
	    // pedroOrder.addEntry(livanto, 9);
	    // pedroOrder.addEntry(roma, 3);

	    // new CoffeeBatch(davidOrder, sergioOrder, pedroOrder);
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
