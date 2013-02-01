package pt.ist.bennu.coffee.manager.rest;

import pt.ist.bennu.bennu.core.rest.AbstractResource;
import pt.ist.bennu.coffee.manager.adapter.CoffeeManagerJsonSerializer;
import pt.ist.bennu.coffee.manager.domain.CoffeeManager;
import pt.ist.bennu.core.domain.Bennu;

public abstract class CoffeeManagerAbstractResource extends AbstractResource {

	static {
		setSerializer(new CoffeeManagerJsonSerializer());
		setDeserializer(new CoffeeManagerJsonDeSerializer());
	}

	protected CoffeeManager getCoffeeManager() {
		return Bennu.getInstance().getCoffeeManager();
	}

}