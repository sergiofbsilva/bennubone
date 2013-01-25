package pt.ist.bennubone.coffee.dto.mapper;

import java.lang.reflect.Type;

import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.service.Service;
import pt.ist.bennubone.coffee.domain.Person;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class UserSerializer implements JsonSerializer<User> {

	@Override
	public JsonElement serialize(User user, Type type, JsonSerializationContext ctx) {
		if (!user.hasPerson()) {
			populateUserPerson(user);
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", user.getExternalId());
		jsonObject.addProperty("name", user.getPerson().getName());
		jsonObject.addProperty("avatar", user.getPerson().getAvatar());
		jsonObject.addProperty("isCoffeeManager", true);

		return jsonObject;
	}

	@Service
	public void populateUserPerson(User user) {
		user.setPerson(new Person("David Martinho"));
		user.setEmail("davidmartinho@gmail.com");
	}
}
