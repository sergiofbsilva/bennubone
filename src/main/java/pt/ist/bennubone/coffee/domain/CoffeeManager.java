package pt.ist.bennubone.coffee.domain;

import pt.ist.fenixframework.FenixFramework;

public class CoffeeManager extends CoffeeManager_Base {

    public CoffeeManager() {
	super();
	    addUser(new User("David", "Martinho", "redox"));
	    addUser(new User("Sérgio", "Silva", "ashtray"));
	    addUser(new User("Pedro", "Santos", "kirk"));
    }

    public static CoffeeManager getInstance() {
	return FenixFramework.getRoot();
    }

}
