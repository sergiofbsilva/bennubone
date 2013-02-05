package pt.ist.bennu.coffee.manager.rest;

import pt.ist.bennu.bennu.core.rest.AbstractResource;
import pt.ist.bennu.coffee.manager.domain.CoffeeManager;

public abstract class CoffeeManagerAbstractResource extends AbstractResource {

    protected CoffeeManager getCoffeeManager() {
        return CoffeeManager.getInstance();
    }

}
