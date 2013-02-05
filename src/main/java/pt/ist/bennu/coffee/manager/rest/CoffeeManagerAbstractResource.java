package pt.ist.bennu.coffee.manager.rest;

import org.joda.time.DateTime;

import pt.ist.bennu.bennu.core.rest.AbstractResource;
import pt.ist.bennu.bennu.core.rest.serializer.JsonAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeBatchAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeItemAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeOrderAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeOrderEntrySerializer;
import pt.ist.bennu.coffee.manager.adapter.DateTimeSerializer;
import pt.ist.bennu.coffee.manager.domain.CoffeeBatch;
import pt.ist.bennu.coffee.manager.domain.CoffeeItem;
import pt.ist.bennu.coffee.manager.domain.CoffeeManager;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrder;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrderEntry;

public abstract class CoffeeManagerAbstractResource extends AbstractResource {

	protected CoffeeManager getCoffeeManager() {
		return CoffeeManager.getInstance();
	}

}