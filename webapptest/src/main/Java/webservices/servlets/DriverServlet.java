package webservices.servlets;

import DAO.BaggageDAOImpl;
import DAO.DriverDAOImpl;
import DAO.OrderRouteDAOImpl;
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
            driverLogic =
                    new DriverLogic(new DriverDAOImpl((Class<Driver>) Class.forName("model.Driver")));
            orderLogic =
                    new OrderLogic(new BaggageDAOImpl((Class<Baggage>) Class.forName("model.Baggage")),
                            new OrderRouteDAOImpl((Class<OrderRoute>) Class.forName("model.OrderRoute")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
        //resp.set
        Driver driver = driverLogic.getById(id);
        if(driver == null) {
            resp.sendRedirect("error.jsp");
        }

        List<Driver> friends = new ArrayList<>();
        friends.addAll(driverLogic.getFriends(id));

        friends.remove(driver);

//        Truck truck =
        List<DriverPageView> route =  orderLogic.getDriverPageView(id);
        req.setAttribute("driver", driver);
        req.setAttribute("friends", friends);
        req.setAttribute("route", route);

        int orgerId = driver.getOrderRoute();
        String truckId  = orderLogic.getTruckId(orgerId);
        req.setAttribute("orgerId", orgerId);
        req.setAttribute("truckId", truckId);

        RequestDispatcher rd = req.getRequestDispatcher("driver.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
