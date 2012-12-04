package pt.ist.bennubone.coffee.filter;

import pt.ist.bennubone.coffee.util.Bootstrap;
import java.io.IOException;
import javax.servlet.*;
import pt.ist.fenixframework.pstm.Transaction;

public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Bootstrap.run();
        Transaction.begin();
        try {
            chain.doFilter(request, response);
        } catch(Exception e) {
            Transaction.abort();
            throw (ServletException)e;
        }
        Transaction.commit();
    }

    @Override
    public void destroy() {}

}
