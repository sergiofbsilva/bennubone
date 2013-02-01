package pt.ist.bennu.coffee.manager.rest;

import pt.ist.bennu.bennu.core.rest.AbstractResource;
import pt.ist.bennu.coffee.manager.adapter.CoffeeManagerJsonDeserializer;
import pt.ist.bennu.coffee.manager.adapter.CoffeeManagerJsonSerializer;
import pt.ist.bennu.coffee.manager.domain.CoffeeManager;

public abstract class CoffeeManagerAbstractResource extends AbstractResource {

	static {
		setSerializer(new CoffeeManagerJsonSerializer());
		setDeserializer(new CoffeeManagerJsonDeserializer());
	}

	protected CoffeeManager getCoffeeManager() {
		return CoffeeManager.getInstance();
	}

}