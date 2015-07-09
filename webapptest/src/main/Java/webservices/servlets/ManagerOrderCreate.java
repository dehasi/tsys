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
            CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityLogic.getAllCities();

            req.setAttribute("cities", cities);
            req.setAttribute("count", count);
            RequestDispatcher rd = req.getRequestDispatcher("orderCreate.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
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
            TruckLogic truckLogic = BusinessFactory.getInstance().getTruckLogic();
            DriverLogic driverLogic = BusinessFactory.getInstance().getDriverLogic();
            CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
            MapLogic mapLogic = BusinessFactory.getInstance().getMapLogic();
            City city = cityLogic.getCityById(statCityId);

            Set<Truck> trucks = truckLogic.getFitTrucks(maxWeight, city);
            int[] array = road.stream().mapToInt(i->i).toArray();
            int roadLenght = mapLogic.getRoadLength(array);

            Set<Driver> drivers = driverLogic.getFitDrivers(roadLenght, city);

            Gson gson = new Gson();
            String truckjs =  gson.toJson(trucks);
            String driversjs = gson.toJson(drivers);
            writeAnswer(req, resp, truckjs + driversjs);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    private void writeAnswer(HttpServletRequest req, HttpServletResponse res, String answer) throws IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/json");
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
