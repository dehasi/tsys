package webservices.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import businessLogic.*;
import model.*;

/**
 * Servlet for dynamically locale change
 */

public class LocaleServlet  extends HttpServlet{
    Logger logger = Logger.getLogger(LocaleServlet.class);
    private static final String EN = "EN";
    private static final String RU = "RU";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        HttpSession session = req.getSession();
        if (lang.compareToIgnoreCase(RU) == 0) {


            session.setAttribute("locale", RU);

        }else {

            session.setAttribute("locale", EN);
        }


        resp.setStatus(resp.SC_OK);

    }
}
