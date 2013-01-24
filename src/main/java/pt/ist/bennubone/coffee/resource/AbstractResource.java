package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.core.security.UserView;
import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.dto.mapper.BennuBoneGsonBuilder;
import pt.ist.bennubone.coffee.exception.BennuBoneException;
import pt.ist.bennubone.coffee.exception.CoffeeManagerError;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.pstm.AbstractDomainObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractResource {

	protected static BennuBoneGsonBuilder builder;

	static {
		builder = new BennuBoneGsonBuilder();
	}

	@Context
	private SecurityContext securityContext;

	protected <T extends DomainObject> T readDomainObject(final String externalId) {
		boolean error = false;
		try {
			T obj = AbstractDomainObject.fromExternalId(externalId);
			if (obj == null) {
				error = true;
			} else {
				return obj;
			}
		} catch (Throwable t) {
			error = true;
		} finally {
			if (error) {
				throw new BennuBoneException(CoffeeManagerError.DOMAIN_OBJECT_NOT_FOUND);
			}
		}
		System.out.println("Unreachable code");
		return null;
	}

	public User getRequestAuthor() {
		User user = UserView.getUser();
		if (user == null) {
			throw new BennuBoneException(CoffeeManagerError.UNAUTHORIZED);
		} else {
			return user;
		}
	}

	protected CoffeeManager getCoffeeManager() {
		return CoffeeManager.getInstance();
	}

	protected String loadJsonStringFor(Object object) {
		return builder.build(object);
	}

	protected String loadJsonStringFromExternalId(String externalId) {
		return loadJsonStringFor(readDomainObject(externalId));
	}

	private JsonElement loadJsonTree(Object object) {
		return builder.buildJsonTree(object);
	}

	protected <T extends DomainObject> T createFromJson(String jsonData, Class<T> clazz) {
		return builder.getGson().fromJson(jsonData, clazz);
	}

	protected <T extends DomainObject> T editFromJson(String externalId, String jsonData, Class<T> clazz) {
		T domainObject = AbstractDomainObject.fromExternalId(externalId);
		return domainObject;

	}

	protected String toJson(JsonElement jsonElement) {
		return builder.getGson().toJson(jsonElement);
	}

	protected String toJson(String field, Object obj) {
		final JsonObject jsonObj = new JsonObject();
		final JsonElement jsonTree = loadJsonTree(obj);
		jsonObj.add(field, jsonTree);
		return toJson(jsonObj);
	}
}