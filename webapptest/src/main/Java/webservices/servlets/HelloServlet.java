package webservices.servlets;

import DAO.TruckDAOImpl;
import businessLogic.TruckLogic;
import model.Truck;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Created by Rafa on 29.06.2015.
 */
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("attribute = " + req.getAttribute("key"));

        TruckLogic truckLogic = null;
        try {
            truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Set<Truck> trucks =  truckLogic.getFitTrucks(6);
        out.println(trucks);

    }
}
