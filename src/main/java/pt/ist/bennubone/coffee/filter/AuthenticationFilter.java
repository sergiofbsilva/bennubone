package pt.ist.bennubone.coffee.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.bennubone.coffee.domain.User;

import com.sun.jersey.core.util.Base64;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    private class AuthenticatedRequestWrapper extends HttpServletRequestWrapper {

	private final String userOid;

	public AuthenticatedRequestWrapper(HttpServletRequest request, String userOid) {
	    super(request);
	    this.userOid = userOid;
	}

	@Override
	public Principal getUserPrincipal() {
	    return new Principal() {

		@Override
		public String getName() {
		    return userOid;

		}
	    };
	}
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	    ServletException {
	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
	if (authHeader == null) {
	    chain.doFilter(request, response);
	} else {
	    String auth = authHeader.substring(SecurityContext.BASIC_AUTH.length());
	    String[] values = Base64.base64Decode(auth).split(":");

	    String email = values[0];
	    String password = values[1];

	    User user = CoffeeManager.getInstance().login(email, password);
	    if (user == null) {
		chain.doFilter(new AuthenticatedRequestWrapper((HttpServletRequest) request, null), response);
	    } else {
		chain.doFilter(new AuthenticatedRequestWrapper((HttpServletRequest) request, user.getExternalId()), response);
	    }
	}
    }

    @Override
    public void destroy() {
    }

}