package pt.ist.bennubone.coffee.domain;

import pt.ist.fenixframework.FenixFramework;

public class CoffeeManager extends CoffeeManager_Base {

    public CoffeeManager() {
	super();
    }

    public static CoffeeManager getInstance() {
	return FenixFramework.getRoot();
    }

}
