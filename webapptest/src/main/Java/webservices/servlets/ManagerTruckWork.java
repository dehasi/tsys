package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.TruckLogic;
import model.Truck;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 04.07.2015.
 */
public class ManagerTruckWork extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String show = req.getParameter("show");
        req.setAttribute("show", show);

        try {
            TruckLogic truckLogic = BusinessFactory.getInstance().getTruckLogic();

            Set<Truck> trucks = null;

            switch (show){
                case "all" : {
                    trucks = truckLogic.getAllTrucks();
                    break;
                }
                case "ok" : {
                    trucks = truckLogic.getOKTrucks();
                    break;
                }
                case "defective" : {
                    trucks = truckLogic.getDefectiveTrucks();
                    break;
                }
                case "free" : {
                    trucks = truckLogic.getFreeTrucks();
                    break;
                }
                case "inorder" : {
                    trucks = truckLogic.getInOrderTrucks();
                    break;
                }
                default: {
                    trucks = truckLogic.getAllTrucks();
                }
            }

            List<Truck> list = new ArrayList<Truck>(trucks);
            req.setAttribute("trucks", list);
            RequestDispatcher rd = req.getRequestDispatcher("truck.jsp");
            rd.forward(req, resp);

        } catch (ClassNotFoundException e) {
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}
