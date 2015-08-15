package Controlers;

import businessLogic.*;
import com.google.gson.*;
import model.City;
import model.Driver;
import model.Truck;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class represents util functions for order work
 */
@Component
public class ManagerOrderUtils {

    private static Logger logger = Logger.getLogger(ManagerOrderUtils.class);

    @Autowired
    OrderService orderService;
    @Autowired
    CityService cityService;
    @Autowired
    TruckService truckService;
    @Autowired
    DriverService driverService;
    @Autowired
    MapService mapService;

    public ManagerOrderUtils() {
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public CityService getCityService() {
        return cityService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public TruckService getTruckService() {
        return truckService;
    }

    public void setTruckService(TruckService truckService) {
        this.truckService = truckService;
    }

    public DriverService getDriverService() {
        return driverService;
    }

    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    public MapService getMapService() {
        return mapService;
    }

    public void setMapService(MapService mapService) {
        this.mapService = mapService;
    }

    protected ModelAndView mainPage(Map<String,String> requestParams) throws IOException {

        String action = requestParams.get("action");
        if(action == null) {
            action = "show";
        }
        switch (action) {
            case "show" :{
                return show(requestParams);
            }
            default:{
                return show(requestParams);
            }
        }
    }

    private ModelAndView show(Map<String,String> requestParams) throws  IOException {
        ModelAndView view = new ModelAndView("m/order");
        String show = requestParams.get("show");
        if(show == null) {
            show = "all";
        }
        try {
            Set<OrderRouteView> orders;

            switch (show){
                case "all" : {
                    orders = orderService.getAllRouteViews();
                    break;
                }
                case "done" : {
                    orders = orderService.getRouteViewsByStatus(1);
                    break;
                }
                case "notdone" : {
                    orders = orderService.getRouteViewsByStatus(0);
                    break;
                }
                default: {
                    orders = orderService.getAllRouteViews();
                }
            }
            view.addObject("orders", orders);
            return view;

        }  catch (Exception e) {
            return UtilsController.handleError(e);
        }
    }




    protected ModelAndView doGet(Map<String,String> requestParams) throws  IOException {
        try {
            int count = Integer.parseInt( requestParams.get("count"));
            Set<City> cities = cityService.getAllCities();
            ModelAndView view = new ModelAndView("m/orderCreate");

            view.addObject("cities", cities);
            view.addObject("count", count);
            return view;

        } catch (Exception e) {
            return UtilsController.handleError(e);
        }
    }


    protected ModelAndView createOrder(Map<String,String> requestParams) throws IOException {
        String action = requestParams.get("do");
        logger.info("Doing post");
        switch (action) {
           case  "createOrder" : {
                logger.info("createOrder begin");
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray ticketsJS = parser.parse(requestParams.get("jsdata")).getAsJsonArray();
                List<Ticket> tickets = new ArrayList<>();
                for (JsonElement je : ticketsJS) {
                    Ticket t = gson.fromJson(je, Ticket.class);
                    tickets.add(t);
                }

                String truckId = requestParams.get("truckId").replace("\"", "");

                JsonArray driverJS = parser.parse(requestParams.get("drivers")).getAsJsonArray();
                List<Integer> drivers = new ArrayList<>();

                for (JsonElement je : driverJS) {
                    Integer i = gson.fromJson(je, Integer.class);
                    drivers.add(i);
                }

                try {
                    orderService.createOrder(tickets, drivers, truckId);
                } catch (SQLException e) {
                    return UtilsController.handleError(e);
                }
                logger.info("createOrder end");
                break;
            }
            default: break;
        }
        return new ModelAndView("redirect:/m/order?action=show&show=all");
    }

    public @ResponseBody
    String getStuff(Map<String,String> requestParams) throws IOException {
        String jsonString = requestParams.get("jsdata");
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

            City city = cityService.getCityById(statCityId);


            Set<Truck> trucks = truckService.getFitTrucks(maxWeight, city);
            int[] array = road.stream().mapToInt(i->i).toArray();
            int roadLength = mapService.getRoadLength(array);

            Set<Driver> drivers = driverService.getFitDrivers(roadLength, city);

            Gson gson = new Gson();
            String truckjs =  gson.toJson(trucks);
            String driversjs = gson.toJson(drivers);
            String driverTimejs = gson.toJson(driverService.getRoadHours(roadLength));
            String answer = "{" +
                    "\"driverTimejs\":" + driverTimejs + "," +
                    "\"trucks\":" + truckjs + "," +
                    "\"drivers\":" + driversjs +
                    "}";
            return  answer;

        } catch (Exception e) {
            return UtilsController.handleError(e).toString(); //FIX: piece of horrible shit
        }

    }

}
