package pt.ist.bennu.coffee.manager.adapter;

import pt.ist.bennu.bennu.core.rest.mapper.AbstractJsonSerializer;
import pt.ist.bennu.coffee.manager.domain.CoffeeBatch;
import pt.ist.bennu.coffee.manager.domain.CoffeeItem;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrder;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrderEntry;

import com.google.gson.GsonBuilder;

public final class CoffeeManagerJsonSerializer extends AbstractJsonSerializer {

	public CoffeeManagerJsonSerializer() {
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		builder.setPrettyPrinting();
		builder.registerTypeAdapter(CoffeeOrder.class, new CoffeeOrderAdapter());
		builder.registerTypeAdapter(CoffeeItem.class, new CoffeeItemAdapter());
		builder.registerTypeAdapter(CoffeeBatch.class, new CoffeeBatchAdapter());
		builder.registerTypeAdapter(CoffeeOrderEntry.class, new CoffeeOrderEntrySerializer());
		setGson(builder.create());
	}
}
