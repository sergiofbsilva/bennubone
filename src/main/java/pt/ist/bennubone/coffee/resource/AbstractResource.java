package pt.ist.bennubone.coffee.resource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.domain.error.CoffeeErrorCode;
import pt.ist.bennubone.coffee.dto.mapper.BennuBoneGsonBuilder;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.pstm.AbstractDomainObject;

import com.google.gson.JsonElement;

public abstract class AbstractResource {

    protected static BennuBoneGsonBuilder builder;

    static {
	builder = new BennuBoneGsonBuilder();
    }

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
		throw new WebApplicationException(Response.status(Status.NOT_FOUND)
			.entity("Couldn't find domain object with oid : " + externalId).build());
	    }
	}
	System.out.println("Unreachable code");
	return null;
    }

    protected Response errorResponse(CoffeeErrorCode errorCode) {
	return Response.status(errorCode.getResponseStatusCode()).entity(loadJsonStringFor(errorCode)).build();
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

    protected JsonElement loadJsonTree(Object object) {
	return builder.buildJsonTree(object);
    }

    protected <T extends DomainObject> T createFromJson(String jsonData, Class<T> clazz) {
	return builder.getGson().fromJson(jsonData, clazz);
    }

    protected <T extends DomainObject> T editFromJson(String externalId, String jsonData, Class<T> clazz) {
	T domainObject = AbstractDomainObject.fromExternalId(externalId);
	return domainObject;

    }
}