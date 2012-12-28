package pt.ist.bennubone.coffee.resource;

import java.security.Principal;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.domain.User;
import pt.ist.bennubone.coffee.dto.mapper.BennuBoneGsonBuilder;
import pt.ist.bennubone.coffee.exception.BennuBoneException;
import pt.ist.bennubone.coffee.exception.CoffeeManagerError;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.pstm.AbstractDomainObject;

import com.google.gson.JsonElement;

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
	Principal principal = securityContext.getUserPrincipal();
	if (principal == null || principal.getName() == null) {
	    throw new BennuBoneException(CoffeeManagerError.NOT_AUTHORIZED);
	}
	return readDomainObject(securityContext.getUserPrincipal().getName());
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