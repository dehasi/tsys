package webservices.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Rafa on 01.07.2015.
 */
public class LoginFilter    implements Filter {

    private static Logger logger = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("filter is working");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

//        HttpSession httpSession = servletRequest.getSe
        httpResponse.sendRedirect("/login.jsp");
    }

    @Override
    public void destroy() {

    }


}
