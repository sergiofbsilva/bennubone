package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.CoffeeOrder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CoffeeOrderSerializer implements JsonSerializer<CoffeeOrder> {

    @Override
    public JsonElement serialize(CoffeeOrder coffeeOrder, Type type, JsonSerializationContext ctx) {
	JsonObject jsonObject = new JsonObject();
	jsonObject.addProperty("id", coffeeOrder.getExternalId());
	jsonObject.add("owner", ctx.serialize(coffeeOrder.getUser()));
	return jsonObject;
    }

}
