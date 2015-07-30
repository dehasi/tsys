package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.CityService;
import businessLogic.TruckService;
import model.City;
import model.Truck;
import model.statuses.TruckStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import businessLogic.*;
import model.*;

/**
 * Created by Rafa on 04.07.2015.
 */
public class ManagerTruckWork extends HttpServlet {
    private static Logger logger = Logger.getLogger(ManagerTruckWork.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "show" :{
                show( req,  resp);
                break;
            }
            case "add" :{
                add(req, resp);
                break;
            }
            case "edit" :{
                edit(req, resp);
                break;
            }
            case "delete" :{
                delete(req, resp);
                break;
            }
            default:{
                show(req, resp);
            }
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TruckService truckService = BusinessFactory.getInstance().getTruckLogic();
            String id = req.getParameter("id");
            Truck truck = truckService.getTruckById(id);
            truckService.deleteTruck(truck);
            resp.sendRedirect("/private/manager/truck?action=show&show=all");
        } catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TruckService truckService = BusinessFactory.getInstance().getTruckLogic();
            CityService cityService = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityService.getAllCities();
            req.setAttribute("cities",cities);

            String id = req.getParameter("id");

            Truck truck = truckService.getTruckById(id);
            req.setAttribute("truck", truck);
            RequestDispatcher rd = req.getRequestDispatcher("truckEdit.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CityService cityService = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityService.getAllCities();
            req.setAttribute("cities",cities);
            RequestDispatcher rd = req.getRequestDispatcher("truckAdd.jsp");
            rd.forward(req, resp);

        }  catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String show = req.getParameter("show");
        if(show == null) {
            show = "all";
        }
        req.setAttribute("show", show);

        try {
            TruckService truckService = BusinessFactory.getInstance().getTruckLogic();

            Set<Truck> trucks;

            switch (show){
                case "all" : {
                    trucks = truckService.getAllTrucks();
                    break;
                }
                case "ok" : {
                    trucks = truckService.getOKTrucks();
                    break;
                }
                case "defective" : {
                    trucks = truckService.getDefectiveTrucks();
                    break;
                }
                case "free" : {
                    trucks = truckService.getFreeTrucks();
                    break;
                }
                case "inorder" : {
                    trucks = truckService.getInOrderTrucks();
                    break;
                }
                default: {
                    trucks = truckService.getAllTrucks();
                }
            }

            req.setAttribute("trucks", trucks);
            RequestDispatcher rd = req.getRequestDispatcher("truck.jsp");
            rd.forward(req, resp);

        }  catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("do");
        if (action == null) {
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }

        switch (action) {
            case "Add": {
                try {
                    TruckService truckService = BusinessFactory.getInstance().getTruckLogic();
                    CityService cityService = BusinessFactory.getInstance().getCityLogic();
                    Truck truck = new Truck();

                    String id = req.getParameter("tid");
                    int duty = Integer.parseInt(req.getParameter("duty"));
                    int capacity = Integer.parseInt(req.getParameter("capacity"));
                    int status = Integer.parseInt(req.getParameter("status"));

                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    truck.setCity(city);
                    truck.setCapacity(capacity);
                    truck.setDutySize(duty);
                    truck.setStatus(TruckStatus.fromInt(status));
                    truck.setId(id);


                    truckService.addTruck(truck);
                    resp.sendRedirect("/private/manager/truck?action=show&show=all");

                }  catch (Exception e) {
                    ServletHelper.handleError(req, resp, e);
                }
                break;
            }
            case "Save" :{
                try {
                    TruckService truckService = BusinessFactory.getInstance().getTruckLogic();
                    CityService cityService = BusinessFactory.getInstance().getCityLogic();
                    String id = req.getParameter("tid");
                    String hiddenId = req.getParameter("hiddenid");
                    if(!id.equals(hiddenId)) {
                        truckService.deleteTruck(truckService.getTruckById(hiddenId));
                    }else {
                        truckService.deleteTruck(truckService.getTruckById(id));
                    }

                    Truck truck = new Truck();

                    int duty = Integer.parseInt(req.getParameter("duty"));
                    int capacity = Integer.parseInt(req.getParameter("capacity"));
                    int status = Integer.parseInt(req.getParameter("status"));

                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    truck.setCity(city);
                    truck.setCapacity(capacity);
                    truck.setDutySize(duty);
                    truck.setStatus(TruckStatus.fromInt(status));
                    truck.setId(id);

                    truckService.addTruck(truck);

                    resp.sendRedirect("/private/manager/truck?action=show&show=all");
                }  catch (Exception e) {
                    ServletHelper.handleError(req, resp, e);
                }
            }
        }
    }

}
