package pt.ist.bennu.coffee.manager.adapter;

import pt.ist.bennu.bennu.core.rest.serializer.JsonSerializer;
import pt.ist.bennu.bennu.core.rest.serializer.Serializer;
import pt.ist.bennu.coffee.manager.domain.CoffeeBatch;
import pt.ist.bennu.coffee.manager.domain.CoffeeItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CoffeeManagerJsonSerializer extends JsonSerializer {

	private final Gson gson;
	private Serializer delegate;

	public CoffeeManagerJsonSerializer(Serializer delegate) {
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		builder.setPrettyPrinting();
		builder.registerTypeAdapter(CoffeeBatch.class, new CoffeeBatchAdapter());
		builder.registerTypeAdapter(CoffeeItem.class, new CoffeeItemAdapter());
		this.gson = builder.create();
		setDelegate(delegate);
	}

}
