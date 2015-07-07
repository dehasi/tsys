package webservices.servlets;

import DAO.BaggageDAOImpl;
import DAO.DriverDAOImpl;
import DAO.OrderRouteDAOImpl;
import businessLogic.BusinessFactory;
import businessLogic.DriverLogic;
import businessLogic.DriverPageView;
import businessLogic.OrderLogic;
import model.Baggage;
import model.Driver;
import model.OrderRoute;
import model.Truck;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DriverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        int id = (int) httpSession.getAttribute("id");
        DriverLogic driverLogic = null;
        OrderLogic orderLogic = null;
        try {
            driverLogic = BusinessFactory.getInstance().getDriverLogic();
            orderLogic = BusinessFactory.getInstance().getOrderLogic();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendRedirect("/error.jsp");
        }

        Driver driver = driverLogic.getById(id);
        if(driver == null) {
            resp.sendRedirect("/error.jsp");
        }

        Integer orderId = driver.getOrderRoute();

        if(orderId != null) {
            List<Driver> friends = new ArrayList<>();
            friends.addAll(driverLogic.getFriends(id));

            friends.remove(driver);

            List<DriverPageView> route = orderLogic.getDriverPageView(id);

            req.setAttribute("friends", friends);
            req.setAttribute("route", route);

            String truckId = orderLogic.getTruckIdByOrder(orderId);
            req.setAttribute("orderId", orderId);
            req.setAttribute("truckId", truckId);
        } else {
            req.setAttribute("route", null);
        }

        req.setAttribute("driver", driver);
        RequestDispatcher rd = req.getRequestDispatcher("driver.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
