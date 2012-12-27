package pt.ist.bennubone.coffee.dto.mapper;

import org.joda.time.DateTime;

import pt.ist.bennubone.coffee.domain.CoffeeBatch;
import pt.ist.bennubone.coffee.domain.CoffeeItem;
import pt.ist.bennubone.coffee.domain.CoffeeOrder;
import pt.ist.bennubone.coffee.domain.CoffeeOrderEntry;
import pt.ist.bennubone.coffee.domain.User;
import pt.ist.bennubone.coffee.domain.error.CoffeeErrorCode;

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
	builder.registerTypeAdapter(CoffeeOrder.class, new CoffeeOrderSerializer());
	builder.registerTypeAdapter(CoffeeItem.class, new CoffeeItemAdapter.CoffeeItemSerializer());
	builder.registerTypeAdapter(CoffeeItem.class, new CoffeeItemAdapter.CoffeeItemDeserializer());
	builder.registerTypeAdapter(CoffeeBatch.class, new CoffeeBatchSerializer());
	builder.registerTypeAdapter(CoffeeOrderEntry.class, new CoffeeOrderEntrySerializer());
	builder.registerTypeAdapter(DateTime.class, new DateTimeSerializer());
	builder.registerTypeHierarchyAdapter(CoffeeErrorCode.class, new CoffeeErrorCodeSerializer());
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
