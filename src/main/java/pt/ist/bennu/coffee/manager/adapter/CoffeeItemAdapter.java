package pt.ist.bennu.coffee.manager.adapter;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import pt.ist.bennu.coffee.manager.domain.CoffeeItem;
import pt.ist.bennu.service.Service;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CoffeeItemAdapter implements JsonSerializer<CoffeeItem>, JsonDeserializer<CoffeeItem> {

	@Override
	public JsonElement serialize(CoffeeItem coffeeItem, Type type, JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", coffeeItem.getExternalId());
		jsonObject.addProperty("name", coffeeItem.getName());
		jsonObject.addProperty("imageUrl", coffeeItem.getImageUrl());
		jsonObject.addProperty("unitPrice", coffeeItem.getUnitPrice());
		jsonObject.addProperty("numUnits", coffeeItem.getNumUnits());
		return jsonObject;
	}

	@Override
	@Service
	public CoffeeItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final String name = jsonObject.get("name").getAsString();
		final String photo = jsonObject.get("imageUrl").getAsString();
		final BigDecimal price = jsonObject.get("unitPrice").getAsBigDecimal();
		final int units = jsonObject.get("units").getAsInt();
		CoffeeItem item = new CoffeeItem(name, price, units);
		item.setImageUrl(photo);
		return item;
	}
}