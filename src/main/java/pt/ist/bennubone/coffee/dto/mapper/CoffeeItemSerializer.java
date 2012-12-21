package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import pt.ist.bennubone.coffee.domain.CoffeeItem;

public class CoffeeItemSerializer implements JsonSerializer<CoffeeItem> {


	@Override
	public JsonElement serialize(CoffeeItem coffeeItem, Type type, JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", coffeeItem.getExternalId());
		jsonObject.addProperty("name", coffeeItem.getName());
		jsonObject.addProperty("name", coffeeItem.getImageUrl());
		jsonObject.addProperty("unitValue", coffeeItem.getUnitValue());
		return jsonObject;
	}
}
