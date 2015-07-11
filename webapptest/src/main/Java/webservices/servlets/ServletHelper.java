package webservices.servlets;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rafa on 10.07.2015.
 */
public class ServletHelper {
    private static Logger logger = Logger.getLogger(ServletHelper.class);
    static void handleError(HttpServletRequest req, HttpServletResponse resp, Exception e) throws ServletException, IOException {
        logger.error(e);
        e.printStackTrace();
        req.setAttribute("error", e);
        RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
        rd.forward(req, resp);
    }
}
