package businessLogic;

import DAO.BaggageDAOImpl;
import DAO.CityDAOImpl;
import DAO.MapDAOImpl;
import DAO.OrderRouteDAOImpl;
import model.Baggage;
import model.City;
import model.OrderRoute;
import model.statuses.BaggageStatus;

import java.sql.SQLException;
import java.util.*;

import static java.util.Collections.*;

/**
 * Created by Rafa on 30.06.2015.
 */
public class OrderLogic {
    BaggageDAOImpl baggageDAO = null;
    OrderRouteDAOImpl orderRouteDAO = null;
    CityDAOImpl cityDAO = null;
    MapDAOImpl mapDAO = null;

    public OrderLogic(BaggageDAOImpl baggageDAO, OrderRouteDAOImpl orderRouteDAO) {
        this.baggageDAO = baggageDAO;
        this.orderRouteDAO = orderRouteDAO;
        try {
            this.cityDAO = new CityDAOImpl((Class<City>) Class.forName("model.City"));
            this.mapDAO = new MapDAOImpl((Class<model.Map>) Class.forName("model.Map"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<OrderRoute> getRoute(int orderId) {

        return orderRouteDAO.getRouteByOrderId(orderId);
    }

    public List<OrderView> getOrderView(int orderId) {

        Set<OrderRoute> routes =  getRoute(orderId);
        List<OrderView> views  = new ArrayList<>();
        for(OrderRoute route :routes) {
            try {
            Baggage baggage = baggageDAO.getById(route.getBaggage());
            City city = cityDAO.getById(route.getCity());

                OrderView view = new OrderView(route.getOrder(),
                        city.getName(),
                        route.getBaggage(),
                        route.getType(),
                        baggage,
                        route.getVisitNumber(),
                        route.getIsDone(), route.getTruck());
                views.add(view);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        sort(views);
        return views;
    }

    public String getTruckIdByOrder(int orderId) {

        return orderRouteDAO.getTruckId(orderId);
    }

    public Integer getOrderIdByTruck(String truckId) {
        OrderRoute route =  orderRouteDAO.getOrderByTruckId(truckId);
        if(route != null) {
            return route.getOrder();
        }
        return null;
    }

    public Set<OrderRoute> getAllRoutes() {
        try {
            return new HashSet<>(orderRouteDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<OrderRouteView> getAllRouteViews() {
        try {
            Set<Integer> ids = orderRouteDAO.getAllOrderIds();
            Set<OrderRouteView> views = new HashSet<>();

            for (Integer id : ids) {
                List<OrderView> list = getOrderView(id);
                Collections.sort(list);
                OrderRouteView view = new OrderRouteView(id,getTruckIdByOrder(id),list);
                views.add(view);
            }
            return views;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    Baggage createBaggage(String name, int weight) {
        Baggage baggage = new Baggage();
        baggage.setName(name);
        baggage.setWeight(weight);
        baggage.setStatus(BaggageStatus.PRODUCED);
        return baggage;
    }

    Integer getRoadLength(Integer[] road) {
        int length = 0;
        for(int i =1 ; i <  road.length; i++) {
            Integer dist = mapDAO.getDistance(i-1, i);
            length += dist;
        }
        return length;
    }
}
