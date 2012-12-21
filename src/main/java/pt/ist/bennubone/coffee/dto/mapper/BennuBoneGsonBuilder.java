package pt.ist.bennubone.coffee.dto.mapper;

import pt.ist.bennubone.coffee.domain.CoffeeBatch;
import pt.ist.bennubone.coffee.domain.CoffeeItem;
import pt.ist.bennubone.coffee.domain.CoffeeOrder;
import pt.ist.bennubone.coffee.domain.CoffeeOrderEntry;
import pt.ist.bennubone.coffee.domain.User;
import pt.ist.bennubone.coffee.domain.exception.CoffeeManagerException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BennuBoneGsonBuilder {

	private static GsonBuilder builder;
	
	static {
		builder = new GsonBuilder();
		builder.serializeNulls();
        builder.setPrettyPrinting();
		builder.registerTypeAdapter(User.class, new UserSerializer());
		builder.registerTypeAdapter(CoffeeOrder.class, new CoffeeOrderSerializer());
		builder.registerTypeAdapter(CoffeeItem.class, new CoffeeItemSerializer());
		builder.registerTypeAdapter(CoffeeBatch.class, new CoffeeBatchSerializer());
		builder.registerTypeAdapter(CoffeeOrderEntry.class, new CoffeeOrderEntrySerializer());
		
		builder.registerTypeHierarchyAdapter(CoffeeManagerException.class, new ExceptionSerializer());

	}
	
	public String build(Object obj) {
		Gson gson = builder.create();
		return gson.toJson(obj);
	}
}
