package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.dto.mapper.BennuBoneGsonBuilder;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.VersionNotAvailableException;

public abstract class AbstractResource {

	protected static BennuBoneGsonBuilder builder;

	static {
		builder = new BennuBoneGsonBuilder();
	}

	protected <T extends DomainObject> T readDomainObject(String externalId) {
		T obj = AbstractDomainObject.fromExternalId(externalId);
		if (obj == null) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return obj;
	}

	protected CoffeeManager getCoffeeManager() {
		return CoffeeManager.getInstance();
	}

	protected String loadJsonStringFor(Object object) {
		return builder.build(object);
	}

	protected String loadJsonStringFromExternalId(String externalId) {
		DomainObject domainObject = CoffeeManager.fromExternalId(externalId);
		return loadJsonStringFor(domainObject);
	}

}