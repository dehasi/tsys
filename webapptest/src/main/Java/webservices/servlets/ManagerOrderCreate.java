package webservices.servlets;

import businessLogic.*;
import com.google.gson.*;
import model.City;
import model.Driver;
import model.Truck;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 07.07.2015.
 */
public class ManagerOrderCreate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int count = Integer.parseInt( req.getParameter("count"));
            CityService cityService = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityService.getAllCities();

            req.setAttribute("cities", cities);
            req.setAttribute("count", count);
            RequestDispatcher rd = req.getRequestDispatcher("orderCreate.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("do");

        switch (action) {
            case  "getStuff" : {
                sendStuff(req, resp);
                break;
            }

            case  "createOrder" : {

                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray ticketsJS = parser.parse(req.getParameter("jsdata")).getAsJsonArray();
                List<Ticket> tickets = new ArrayList<>();
                for (JsonElement je : ticketsJS) {
                    Ticket t = gson.fromJson(je, Ticket.class);
                    tickets.add(t);
                }

                String truckId = req.getParameter("truckId").replace("\"", "");

                JsonArray driverJS = parser.parse(req.getParameter("drivers")).getAsJsonArray();
                List<Integer> drivers = new ArrayList<>();

                for (JsonElement je : driverJS) {
                    Integer i = gson.fromJson(je, Integer.class);
                    drivers.add(i);
                }

                OrderService orderService = BusinessFactory.getInstance().getOrderLogic();
                try {
                    orderService.createOrder(tickets, drivers, truckId);
                } catch (SQLException e) {
                    ServletHelper.handleError(req,resp,e);
                }


                System.out.println(req.getParameter("truckId"));
                System.out.println(req.getParameter("drivers"));
                System.out.println(req.getParameter("jsdata"));
                resp.sendRedirect("/private/manager/order?action=show&show=all");
                break;
            }
            default: break;
        }

    }

    private void sendStuff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonString = req.getParameter("jsdata");
        JsonElement root = new JsonParser().parse(jsonString);
        JsonArray elements = root.getAsJsonArray();
        ArrayList<Integer> road = new ArrayList<>();
        int maxWeight = 0;

        for(JsonElement je : elements) {
            JsonObject jo = je.getAsJsonObject();
            String name = jo.get("name").toString().replace("\"", "");
            String weight = jo.get("weight").toString().replace("\"", "");
            String loadId = jo.get("loadId").toString().replace("\"", "");
            String unloadId = jo.get("unloadId").toString().replace("\"", "");

            maxWeight = Integer.max(maxWeight, Integer.parseInt(weight));
            road.add(Integer.parseInt(loadId));
            road.add(Integer.parseInt(unloadId));
        }
        int statCityId = road.get(0);

        try {
            TruckService truckService = BusinessFactory.getInstance().getTruckLogic();
            DriverLogic driverLogic = BusinessFactory.getInstance().getDriverLogic();
            CityService cityService = BusinessFactory.getInstance().getCityLogic();
            MapService mapService = BusinessFactory.getInstance().getMapLogic();
            City city = cityService.getCityById(statCityId);

            Set<Truck> trucks = truckService.getFitTrucks(maxWeight, city);
            int[] array = road.stream().mapToInt(i->i).toArray();
            int roadLenght = mapService.getRoadLength(array);

            Set<Driver> drivers = driverLogic.getFitDrivers(roadLenght, city);

            Gson gson = new Gson();
            String truckjs =  gson.toJson(trucks);
            String driversjs = gson.toJson(drivers);
            String driverTimejs = gson.toJson(42);
            String answer = "{" +
                    "\"driverTimejs\":" + driverTimejs + "," +
                    "\"trucks\":" + truckjs + "," +
                    "\"drivers\":" + driversjs +
                    "}";
            writeAnswer(req, resp, answer);

        } catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }

    }

    private void writeAnswer(HttpServletRequest req, HttpServletResponse res, String answer) throws IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");
        out.write(answer);
        out.close();
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            out.write(paramName);
            out.write("\n");

            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                out.write("\t" + paramValue);
                out.write("\n");
            }

        }
        out.close();
    }
}
