package pt.ist.bennu.coffee.manager.adapter;

import java.lang.reflect.Type;

import pt.ist.bennu.coffee.manager.domain.CoffeeOrderEntry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CoffeeOrderEntrySerializer implements JsonSerializer<CoffeeOrderEntry> {

	@Override
	public JsonElement serialize(CoffeeOrderEntry coffeeOrderEntry, Type type, JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", coffeeOrderEntry.getExternalId());
		jsonObject.addProperty("quantity", coffeeOrderEntry.getQuantity());
		jsonObject.add("item", ctx.serialize(coffeeOrderEntry.getItem()));
		jsonObject.addProperty("total", coffeeOrderEntry.getTotal().setScale(2));
		return jsonObject;
	}
}