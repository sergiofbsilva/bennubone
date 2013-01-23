package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.CoffeeBatch;
import pt.ist.bennubone.coffee.domain.CoffeeOrder;
import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.pstm.AbstractDomainObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CoffeeBatchAdapter implements JsonSerializer<CoffeeBatch>, JsonDeserializer<CoffeeBatch> {

	@Override
	public JsonElement serialize(CoffeeBatch coffeeBatch, Type type, JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", coffeeBatch.getExternalId());
		jsonObject.addProperty("shippingCharges", coffeeBatch.getShippingCharges());
		jsonObject.addProperty("numCapsules", coffeeBatch.getTotalCount());
		jsonObject.add("creationTimestamp", ctx.serialize(coffeeBatch.getCreationTimestamp()));
		jsonObject.add("sentTimestamp", ctx.serialize(coffeeBatch.getSentTimestamp()));
		jsonObject.add("receivedTimestamp", ctx.serialize(coffeeBatch.getReceivedTimestamp()));
		jsonObject.add("orders", ctx.serialize(coffeeBatch.getCoffeeOrder()));
		return jsonObject;
	}

	@Override
	@Service
	public CoffeeBatch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		CoffeeBatch batch = new CoffeeBatch();
		final JsonArray orders = jsonObject.get("orders").getAsJsonArray();
		for (JsonElement order : orders) {
			batch.addCoffeeOrder((CoffeeOrder) AbstractDomainObject.fromExternalId(order.getAsString()));
		}
		return batch;
	}
}
