package pt.ist.bennubone.coffee.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/test")
public class TestResource {

	Logger logger = LoggerFactory.getLogger(TestResource.class);

	@Context
	HttpServletRequest request;

	@Path("/{text}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(@PathParam("text") String value) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession(true);
			logger.trace("creating session {}", session.getId());
		}
		final Object attribute = session.getAttribute("xpto");
		if (attribute == null) {
			session.setAttribute("xpto", value);
			logger.trace("set attribute to session {}", session.getId());
		}
		logger.trace("reading from session {} value xpto {}", session.getId(), session.getAttribute("xpto"));
		return "ok";
	}
}
