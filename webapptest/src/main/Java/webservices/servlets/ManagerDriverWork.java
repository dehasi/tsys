package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.CityLogic;
import businessLogic.DriverLogic;
import model.City;
import model.Driver;
import model.statuses.DriverStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Rafa on 05.07.2015.
 */
public class ManagerDriverWork extends HttpServlet {

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String show = req.getParameter("show");
        req.setAttribute("show", show);
        try {
            req.setAttribute("show", show);
            DriverLogic  driverLogic = BusinessFactory.getInstance().getDriverLogic();

            Set<Driver> drivers = null;

            switch (show){
                case "all" : {
                    drivers = driverLogic.getAllDrivers();
                    break;
                }
                case "free" : {
                    drivers = driverLogic.getFreeDrivers();
                    break;
                }
                case "inorder" : {
                    drivers = driverLogic.getInOrderDrivers();
                    break;
                }
                default: {
                    drivers = driverLogic.getAllDrivers();
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityLogic.getAllCities();
            req.setAttribute("cities",cities);
            RequestDispatcher rd = req.getRequestDispatcher("driverAdd.jsp");
            rd.forward(req, resp);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DriverLogic driverLogic = BusinessFactory.getInstance().getDriverLogic();
            CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityLogic.getAllCities();
            req.setAttribute("cities",cities);

            String id = req.getParameter("id");
            int driverId = Integer.parseInt(id);
            Driver driver =  driverLogic.getById(driverId);
            req.setAttribute("driver", driver);
            RequestDispatcher rd = req.getRequestDispatcher("driverEdit.jsp");
            rd.forward(req, resp);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);

        } catch (ServletException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }catch (NullPointerException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DriverLogic driverLogic = BusinessFactory.getInstance().getDriverLogic();

            String id = req.getParameter("id");
            int driverId = Integer.parseInt(id);
            Driver driver =  driverLogic.getById(driverId);

            driverLogic.deleteDriver(driver);
            resp.sendRedirect("/private/manager/driver?action=show&show=all");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);

        } catch (NullPointerException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
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

        }

        switch (action) {
            case "Add": {

                try {
                    DriverLogic driverLogic = BusinessFactory.getInstance().getDriverLogic();
                    CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
                    Driver driver = new Driver();
                    String name = req.getParameter("name");
                    String lastName = req.getParameter("lastname");
                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityLogic.getCityById(cityId);

                    driver.setName(name);
                    driver.setLastName(lastName);
                    driver.setOrderRoute(null);
                    driver.setStatus(DriverStatus.REST);
                    driver.setHoursWorked(0);
                    driver.setCity(city);

                    driverLogic.addNewDriver(driver);
                    resp.sendRedirect("/private/manager/driver?action=show&show=all");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    req.setAttribute("error", e);
                    RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                    req.setAttribute("error", e);
                    RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);
                }
                break;
            }
            case "Save" :{
                try {
                    DriverLogic driverLogic = BusinessFactory.getInstance().getDriverLogic();
                    CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
                    String id = req.getParameter("id");
                    int driverId = Integer.parseInt(id);
                    Driver driver =  driverLogic.getById(driverId);

                    String name = req.getParameter("name");
                    String lastName = req.getParameter("lastname");
                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityLogic.getCityById(cityId);

                    driver.setName(name);
                    driver.setLastName(lastName);
                    driver.setCity(city);

                    driverLogic.updateDriver(driver);
                    resp.sendRedirect("/private/manager/driver?action=show&show=all");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    req.setAttribute("error", e);
                    RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    req.setAttribute("error", e);
                    RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                    req.setAttribute("error", e);
                    RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


}
