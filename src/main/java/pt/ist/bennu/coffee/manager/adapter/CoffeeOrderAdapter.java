package pt.ist.bennu.coffee.manager.adapter;

import pt.ist.bennu.coffee.manager.domain.CoffeeItem;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrder;
import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.json.JsonAdapter;
import pt.ist.bennu.json.JsonBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CoffeeOrderAdapter implements JsonAdapter<CoffeeOrder> {

    @Override
    public CoffeeOrder create(JsonObject jsonObject, JsonBuilder jsonRegistry) {
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

    @Override
    public JsonElement view(CoffeeOrder coffeeOrder, JsonBuilder ctx) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", coffeeOrder.getExternalId());
        jsonObject.add("owner", ctx.view(coffeeOrder.getUser()));
        jsonObject.add("entries", ctx.view(coffeeOrder.getEntry()));
        jsonObject.addProperty("total", String.format("%.2f", coffeeOrder.getTotal()));
        jsonObject.addProperty("boxes", coffeeOrder.getNumBoxes());
        jsonObject.addProperty("count", coffeeOrder.getCount());
        jsonObject.addProperty("batched", coffeeOrder.isBatched());
        jsonObject.addProperty("sent", coffeeOrder.isSent());
        return jsonObject;

    }

    @Override
    public CoffeeOrder update(JsonObject json, CoffeeOrder obj, JsonBuilder jsonRegistry) {
        return null;
    }
}
