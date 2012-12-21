package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.error.CoffeeErrorCode;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CoffeeErrorCodeSerializer implements JsonSerializer<CoffeeErrorCode> {

	@Override
	public JsonElement serialize(CoffeeErrorCode errorCode, Type type,
			JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("errorCode", errorCode.getErrorCode());
		jsonObject.addProperty("errorDescription", errorCode.getMessage());
		return jsonObject;
	}

}
