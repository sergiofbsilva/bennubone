package pt.ist.bennubone.coffee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import pt.ist.fenixframework.pstm.RequestInfo;
import pt.ist.fenixframework.pstm.Transaction;

public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	    ServletException {
	try {
	    Transaction.begin(true);
	    Transaction.currentFenixTransaction().setReadOnly();
	    chain.doFilter(request, response);
	} finally {
	    Transaction.forceFinish();
	    RequestInfo.clear();
	}
    }

    @Override
    public void destroy() {
    }

}
