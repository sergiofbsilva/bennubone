package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.core.domain.groups.DynamicGroup;
import pt.ist.bennu.core.domain.groups.GroupException;
import pt.ist.bennu.core.domain.groups.PersistentGroup;
import pt.ist.bennubone.coffee.exception.BennuBoneException;
import pt.ist.bennubone.coffee.exception.CoffeeManagerError;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class UserSerializer implements JsonSerializer<User> {

	@Override
	public JsonElement serialize(User user, Type type, JsonSerializationContext ctx) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", user.getExternalId());
		jsonObject.addProperty("name", user.getPresentationName());
		PersistentGroup coffeeUsersGroup;
		try {
			coffeeUsersGroup = DynamicGroup.getInstance("COFFEE_MANAGERS");
			jsonObject.add("isCoffeeManager", ctx.serialize(coffeeUsersGroup.isMember(user)));

		} catch (GroupException e) {
			throw new BennuBoneException(CoffeeManagerError.COFFEE_USERS_GROUP_NOT_FOUND);
		}
		return jsonObject;
	}
}
