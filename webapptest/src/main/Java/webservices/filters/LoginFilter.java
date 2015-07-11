package webservices.filters;

import model.statuses.UserStatus;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

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

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        try {
            Object status =  session.getAttribute("status");

            if (status == null) {

                ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
                logger.warn("illegal password");
            } else {

                filterChain.doFilter(servletRequest, servletResponse);

            }

        } catch (Exception e) {

            ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");

        }

    }

    @Override
    public void destroy() {

    }


}
