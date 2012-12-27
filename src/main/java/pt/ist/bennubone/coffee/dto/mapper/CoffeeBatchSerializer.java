package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.CoffeeBatch;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CoffeeBatchSerializer implements JsonSerializer<CoffeeBatch> {

    @Override
    public JsonElement serialize(CoffeeBatch coffeeBatch, Type type, JsonSerializationContext ctx) {
	JsonObject jsonObject = new JsonObject();
	jsonObject.addProperty("id", coffeeBatch.getOid());
	jsonObject.addProperty("shippingCharges", coffeeBatch.getShippingCharges());
	jsonObject.addProperty("numCapsules", coffeeBatch.getTotalCount());
	jsonObject.add("creationTimestamp", ctx.serialize(coffeeBatch.getCreationTimestamp()));
	jsonObject.add("sentTimestamp", ctx.serialize(coffeeBatch.getSentTimestamp()));
	jsonObject.add("receivedTimestamp", ctx.serialize(coffeeBatch.getReceivedTimestamp()));

	return jsonObject;
    }

}
