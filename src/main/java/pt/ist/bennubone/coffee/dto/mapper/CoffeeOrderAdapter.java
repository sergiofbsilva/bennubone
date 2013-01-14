package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.CoffeeItem;
import pt.ist.bennubone.coffee.domain.CoffeeOrder;
import pt.ist.bennubone.coffee.domain.User;
import pt.ist.fenixWebFramework.services.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CoffeeOrderAdapter implements JsonSerializer<CoffeeOrder>, JsonDeserializer<CoffeeOrder> {

    @Override
    public JsonElement serialize(CoffeeOrder coffeeOrder, Type type, JsonSerializationContext ctx) {
	JsonObject jsonObject = new JsonObject();
	jsonObject.addProperty("id", coffeeOrder.getExternalId());
	jsonObject.add("owner", ctx.serialize(coffeeOrder.getUser()));
	jsonObject.add("entries", ctx.serialize(coffeeOrder.getEntry()));
	jsonObject.addProperty("total", coffeeOrder.getTotal());
	jsonObject.addProperty("count", coffeeOrder.getCount());
	jsonObject.addProperty("batched", coffeeOrder.isBatched());
	return jsonObject;
    }

    // { "userId" : "12039102932", "entries" : [ { "itemId" : "1203902193", "quantity" : 5 } ] }
    @Override
    @Service
    public CoffeeOrder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	final JsonObject jsonObject = json.getAsJsonObject();
	final String userId = jsonObject.get("userId").getAsString();
	final User user = User.fromExternalId(userId);
	final JsonArray entries = jsonObject.get("entries").getAsJsonArray();
	final CoffeeOrder order = new CoffeeOrder(user);
	for (final JsonElement entry : entries) {
	    final JsonObject entryObj = entry.getAsJsonObject();
	    final String itemId = entryObj.get("itemId").getAsString();
	    final Integer quantity = entryObj.get("quantity").getAsInt();
	    order.addEntry((CoffeeItem) CoffeeItem.fromExternalId(itemId), quantity);
	}
	return order;
    }
}
