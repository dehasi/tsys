package webservices.servlets;

import DAO.UserDAOImpl;
import businessLogic.BusinessFactory;
import businessLogic.UserLogic;
import model.User;
import model.statuses.UserStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static model.statuses.UserStatus.*;

/**
 * Created by Rafa on 01.07.2015.
 */
public class LoginServlet extends HelloServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLogic userLogic = null;
        try {
            userLogic = BusinessFactory.getInstance().getUserLogic();
        } catch (Exception e) {
            e.printStackTrace();

            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, resp);
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(userLogic.isValidUser(login, password)) {
            switch (userLogic.getUserStatus(login)){
                case DRIVER: {
                    HttpSession session = req.getSession();
                    session.setAttribute("status", DRIVER);
                    session.setAttribute("id", Integer.parseInt(login));
                    resp.sendRedirect("private/driver");
                    break;
                }
                case MANAGER:{
                    resp.sendRedirect("private/manager");
                    break;
                }
                default: {
                    resp.sendRedirect("login.jsp");
                }
            }
        }else {
            resp.sendRedirect("login.jsp");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
