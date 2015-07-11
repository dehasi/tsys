package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.CityService;
import businessLogic.DriverService;
import model.City;
import model.Driver;
import model.statuses.DriverStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Rafa on 05.07.2015.
 */
public class ManagerDriverWork extends HttpServlet {
    private static Logger logger = Logger.getLogger(ManagerDriverWork.class);

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String show = req.getParameter("show");
        req.setAttribute("show", show);
        try {
            req.setAttribute("show", show);
            DriverService driverService = BusinessFactory.getInstance().getDriverLogic();

            Set<Driver> drivers = null;

            switch (show){
                case "all" : {
                    drivers = driverService.getAllDrivers();
                    break;
                }
                case "free" : {
                    drivers = driverService.getFreeDrivers();
                    break;
                }
                case "inorder" : {
                    drivers = driverService.getInOrderDrivers();
                    break;
                }
                default: {
                    drivers = driverService.getAllDrivers();
                }
            }
            if (drivers != null){
                List<Driver> dlist = new ArrayList<>(drivers);
                Collections.sort(dlist);
                req.setAttribute("drivers", dlist);
            }else {

            }
            RequestDispatcher rd = req.getRequestDispatcher("driver.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CityService cityService = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityService.getAllCities();
            req.setAttribute("cities", cities);
            RequestDispatcher rd = req.getRequestDispatcher("driverAdd.jsp");
            rd.forward(req, resp);

        }  catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DriverService driverService = BusinessFactory.getInstance().getDriverLogic();
            CityService cityService = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityService.getAllCities();
            req.setAttribute("cities",cities);

            String id = req.getParameter("id");
            int driverId = Integer.parseInt(id);
            Driver driver =  driverService.getById(driverId);
            req.setAttribute("driver", driver);
            RequestDispatcher rd = req.getRequestDispatcher("driverEdit.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DriverService driverService = BusinessFactory.getInstance().getDriverLogic();

            String id = req.getParameter("id");
            int driverId = Integer.parseInt(id);
            Driver driver =  driverService.getById(driverId);

            driverService.deleteDriver(driver);
            resp.sendRedirect("/private/manager/driver?action=show&show=all");

        } catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }


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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("do");
        if (action == null) {
            logger.error("null in action paramenter");
        }

        switch (action) {
            case "Add": {

                try {
                    DriverService driverService = BusinessFactory.getInstance().getDriverLogic();
                    CityService cityService = BusinessFactory.getInstance().getCityLogic();
                    Driver driver = new Driver();
                    String name = req.getParameter("name");
                    String lastName = req.getParameter("lastname");
                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    driver.setName(name);
                    driver.setLastName(lastName);
                    driver.setOrderRoute(null);
                    driver.setStatus(DriverStatus.REST);
                    driver.setHoursWorked(0);
                    driver.setCity(city);

                    driverService.addNewDriver(driver);
                    resp.sendRedirect("/private/manager/driver?action=show&show=all");

                } catch (Exception e) {
                    ServletHelper.handleError(req, resp, e);
                }
                break;
            }
            case "Save" :{
                try {
                    DriverService driverService = BusinessFactory.getInstance().getDriverLogic();
                    CityService cityService = BusinessFactory.getInstance().getCityLogic();
                    String id = req.getParameter("id");
                    int driverId = Integer.parseInt(id);
                    Driver driver =  driverService.getById(driverId);

                    String name = req.getParameter("name");
                    String lastName = req.getParameter("lastname");
                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    driver.setName(name);
                    driver.setLastName(lastName);
                    driver.setCity(city);

                    driverService.updateDriver(driver);
                    resp.sendRedirect("/private/manager/driver?action=show&show=all");
                }  catch (Exception e) {
                    ServletHelper.handleError(req, resp, e);
                }
            }
        }
    }

}
