package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.CoffeeOrderEntry;

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
	return jsonObject;
    }

}
