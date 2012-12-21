package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.exception.CoffeeManagerException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ExceptionSerializer implements JsonSerializer<CoffeeManagerException> {

	@Override
	public JsonElement serialize(CoffeeManagerException exception, Type type,
			JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("errorCode", exception.getErrorCode());
		jsonObject.addProperty("errorDescription", exception.getMessage());
		return jsonObject;
	}

}
