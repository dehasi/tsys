package webservices.servlets;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import businessLogic.*;
import model.*;

/**
 * Created by Rafa on 01.07.2015.
 */
public class ManagerServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ManagerServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("key", "value");
        RequestDispatcher rd = req.getRequestDispatcher("manager/manager.jsp");
        rd.forward(req, resp);
    }

}
