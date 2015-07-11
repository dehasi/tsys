package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static model.statuses.UserStatus.*;
import org.apache.log4j.Logger;
/**
 * Created by Rafa on 01.07.2015.
 */
public class LoginServlet extends HelloServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService  = BusinessFactory.getInstance().getUserLogic();
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        logger.info("logging begin");
        if(userService.isValidUser(login, password)) {
            switch (userService.getUserStatus(login)){
                case DRIVER: {
                    logger.info("driver logged");
                    HttpSession session = req.getSession();
                    session.setAttribute("status", DRIVER);
                    session.setAttribute("id", Integer.parseInt(login));
                    resp.sendRedirect("private/driver");
                    break;
                }
                case MANAGER:{
                    logger.info("manager logged");
                    resp.sendRedirect("private/manager");
                    break;
                }
                default: {
                    logger.error("wrong login");
                    resp.sendRedirect("login.jsp");
                }
            }
        }else {
            resp.sendRedirect("login.jsp");
        }


    }
}
