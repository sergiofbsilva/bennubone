package pt.ist.bennu.coffee.manager.adapter;

import org.joda.time.DateTime;

import pt.ist.bennu.coffee.manager.domain.CoffeeBatch;
import pt.ist.bennu.coffee.manager.domain.CoffeeItem;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrder;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrderEntry;
import pt.ist.bennu.core.domain.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class BennuBoneGsonBuilder {

	private static GsonBuilder builder;
	private static Gson gson;

	static {
		builder = new GsonBuilder();
		builder.serializeNulls();
		builder.setPrettyPrinting();
		builder.registerTypeAdapter(User.class, new UserSerializer());
		builder.registerTypeAdapter(CoffeeOrder.class, new CoffeeOrderAdapter());
		builder.registerTypeAdapter(CoffeeItem.class, new CoffeeItemAdapter());
		builder.registerTypeAdapter(CoffeeBatch.class, new CoffeeBatchAdapter());
		builder.registerTypeAdapter(CoffeeOrderEntry.class, new CoffeeOrderEntrySerializer());
		builder.registerTypeAdapter(DateTime.class, new DateTimeSerializer());

		gson = builder.create();
	}

	public String build(Object obj) {
		return gson.toJson(obj);
	}

	public JsonElement buildJsonTree(Object obj) {
		return gson.toJsonTree(obj);
	}

	public Gson getGson() {
		return gson;
	}
}
