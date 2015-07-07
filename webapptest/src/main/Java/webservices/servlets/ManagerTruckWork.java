package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.CityLogic;
import businessLogic.TruckLogic;
import model.City;
import model.Truck;
import model.statuses.TruckStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Rafa on 04.07.2015.
 */
public class ManagerTruckWork extends HttpServlet {


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
            TruckLogic truckLogic = BusinessFactory.getInstance().getTruckLogic();
            String id = req.getParameter("id");
            Truck truck = truckLogic.getTruckById(id);
            truckLogic.deleteTruck(truck);
            resp.sendRedirect("/private/manager/truck?action=show&show=all");
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

        } catch (IOException e) {
            e.printStackTrace();

            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TruckLogic truckLogic = BusinessFactory.getInstance().getTruckLogic();
            CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityLogic.getAllCities();
            req.setAttribute("cities",cities);

            String id = req.getParameter("id");

            Truck truck = truckLogic.getTruckById(id);
            req.setAttribute("truck", truck);
            RequestDispatcher rd = req.getRequestDispatcher("truckEdit.jsp");
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

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityLogic.getAllCities();
            req.setAttribute("cities",cities);
            RequestDispatcher rd = req.getRequestDispatcher("truckAdd.jsp");
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
        }
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String show = req.getParameter("show");
        if(show == null) {
            show = "all";
        }
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

            req.setAttribute("trucks", trucks);
            RequestDispatcher rd = req.getRequestDispatcher("truck.jsp");
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
//                handleRequest(req, resp);
                try {
                    TruckLogic truckLogic = BusinessFactory.getInstance().getTruckLogic();
                    CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
                    Truck truck = new Truck();

                    String id = req.getParameter("tid");
                    int duty = Integer.parseInt(req.getParameter("duty"));
                    int capacity = Integer.parseInt(req.getParameter("capacity"));
                    int status = Integer.parseInt(req.getParameter("status"));

                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityLogic.getCityById(cityId);

                    truck.setCity(city);
                    truck.setCapacity(capacity);
                    truck.setDutySize(duty);
                    truck.setStatus(TruckStatus.fromInt(status));
                    truck.setId(id);


                    truckLogic.addTruck(truck);
                    resp.sendRedirect("/private/manager/truck?action=show&show=all");

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
                    TruckLogic truckLogic = BusinessFactory.getInstance().getTruckLogic();
                    CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
                    String id = req.getParameter("tid");
                    String hiddenId = req.getParameter("hiddenid");
                    if(!id.equals(hiddenId)) {
                        truckLogic.deleteTruck(truckLogic.getTruckById(hiddenId));
                    }else {
                        truckLogic.deleteTruck(truckLogic.getTruckById(id));
                    }

                    Truck truck = new Truck();

                    int duty = Integer.parseInt(req.getParameter("duty"));
                    int capacity = Integer.parseInt(req.getParameter("capacity"));
                    int status = Integer.parseInt(req.getParameter("status"));

                    String cityP = req.getParameter("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityLogic.getCityById(cityId);

                    truck.setCity(city);
                    truck.setCapacity(capacity);
                    truck.setDutySize(duty);
                    truck.setStatus(TruckStatus.fromInt(status));
                    truck.setId(id);

                    truckLogic.addTruck(truck);

                    resp.sendRedirect("/private/manager/truck?action=show&show=all");
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
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}
