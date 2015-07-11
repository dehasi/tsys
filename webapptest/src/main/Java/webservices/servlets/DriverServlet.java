package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.DriverService;
import businessLogic.OrderView;
import businessLogic.OrderService;
import model.Driver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DriverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        int id = (int) httpSession.getAttribute("id");
        DriverService driverService = null;
        OrderService orderService = null;

        driverService = BusinessFactory.getInstance().getDriverLogic();
        orderService = BusinessFactory.getInstance().getOrderLogic();


        Driver driver = driverService.getById(id);
        if(driver == null) {
            resp.sendRedirect("/error.jsp");
        }

        Integer orderId = driver.getOrderRoute();

        if(orderId != null) {
            List<Driver> friends = new ArrayList<>();
            friends.addAll(driverService.getFriends(id));

            friends.remove(driver);

            List<OrderView> route = orderService.getOrderView(id);

            req.setAttribute("friends", friends);
            req.setAttribute("route", route);

            String truckId = orderService.getTruckIdByOrder(orderId);
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
