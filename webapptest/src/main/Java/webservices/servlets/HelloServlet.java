package webservices.servlets;

import DAO.TruckDAOImpl;
import businessLogic.TruckService;
import model.Truck;

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

        TruckService truckService = null;

        truckService = new TruckService(new TruckDAOImpl(Truck.class));

        Set<Truck> trucks =  truckService.getFitTrucks(6, null);
        out.println(trucks);

    }
}
