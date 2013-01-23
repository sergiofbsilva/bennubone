package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennubone.coffee.domain.Role;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RoleAdapter implements JsonSerializer<Role>, JsonDeserializer<Role> {

	@Override
	public JsonElement serialize(Role role, Type type, JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", role.getExternalId());
		jsonObject.addProperty("name", role.getName());
		return jsonObject;
	}

	@Override
	public Role deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		String externalId = json.getAsJsonObject().get("id").getAsString();
		return Role.fromExternalId(externalId);
	}
}
