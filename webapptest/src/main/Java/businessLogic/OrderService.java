package businessLogic;

import DAO.*;
import model.Baggage;
import model.City;
import model.OrderRoute;
import model.Truck;
import model.statuses.BaggageStatus;
import model.statuses.DriverStatus;
import model.statuses.OrderStatus;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.*;

import static java.util.Collections.*;

/**
 * Class represents business logic for order work
 * Creating oeder and return all statuses of order in
 * different views
 */
public class OrderService {
    BaggageDAOImpl baggageDAO = null;
    OrderRouteDAOImpl orderRouteDAO = null;
    CityDAOImpl cityDAO = null;
    MapDAOImpl mapDAO = null;
    DriverDAOImpl driverDAO = null;

    /**
     * Constructor. creates instance of class
     * @param baggageDAO DAO for baggage working
     * @param orderRouteDAO DAO for order work
     * @param cityDAO DAO for city work
     * @param mapDAO DAO for map work
     * @param driverDAO DAO for driver work
     */
    public OrderService(BaggageDAOImpl baggageDAO, OrderRouteDAOImpl orderRouteDAO,
                        CityDAOImpl cityDAO, MapDAOImpl mapDAO, DriverDAOImpl driverDAO) {
        this.baggageDAO = baggageDAO;
        this.orderRouteDAO = orderRouteDAO;
        this.cityDAO = cityDAO;
        this.mapDAO = mapDAO;
        this.driverDAO = driverDAO;

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

    public Set<OrderRouteView>  getRouteViewsByStatus(int status) {
        Set<OrderRouteView> views = getAllRouteViews();
        Set<OrderRouteView> answer = new HashSet<>();
        for(OrderRouteView orw : views) {
            if (orw.getStatus() == status) {
                answer.add(orw);
            }
        }
        return answer;
    }

    public Set<OrderRouteView> getAllRouteViews() {
        try {
            Set<Integer> ids = orderRouteDAO.getAllOrderIds();
            Set<OrderRouteView> views = new HashSet<>();

            for (Integer id : ids) {
                List<OrderView> list = getOrderView(id);
                int status = countOrderStatus(list);
                Collections.sort(list);
                OrderRouteView view = new OrderRouteView(id, status, getTruckIdByOrder(id),list);
                views.add(view);
            }
            return views;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private int countOrderStatus( List<OrderView> list) {
        for (OrderView o : list) {
            if(o.getIsDone() == 0){
                return 0;
            }
        }
        return 1;
    }


    /**
     * Creates order and write all information in database
     * @param tickets list of order tickets from client
     * @param driverIds array of driver ids that will be do order
     * @param truckId truck for order
     * @throws SQLException
     */
    public void createOrder(List<Ticket> tickets, List<Integer> driverIds, String truckId) throws SQLException {
        List<Baggage> baggages = createBaggageFromTickets(tickets);
        for(Baggage b : baggages){
            baggageDAO.add(b);
        }
        int orderId = orderRouteDAO.getMaxId() + 1;
        List<OrderRoute> routes = new ArrayList<>();

        int baxIndex = 0;
        for (Ticket t : tickets) {
            OrderRoute oLoad = new OrderRoute();
            OrderRoute oUnLoad = new OrderRoute();

            oLoad.setOrder(orderId);
            oLoad.setCity(t.loadId);
            oLoad.setBaggage(baggages.get(baxIndex).getId());
            oLoad.setIsDone(0);
            oLoad.setStatus(0);
            oLoad.setType(0); // load
            oLoad.setVisitNumber(baxIndex+1);
            oLoad.setTruck(truckId);

            oUnLoad.setOrder(orderId);
            oUnLoad.setCity(t.unloadId);
            oUnLoad.setBaggage(baggages.get(baxIndex).getId());
            oUnLoad.setIsDone(0);
            oUnLoad.setStatus(0);
            oUnLoad.setType(1); // unload
            oUnLoad.setVisitNumber(baxIndex + 2);
            oUnLoad.setTruck(truckId);

            routes.add(oLoad);
            routes.add(oUnLoad);
        }

        for (OrderRoute r : routes) {
            orderRouteDAO.add(r);
        }

        for(int ids : driverIds){
            model.Driver driver = driverDAO.getById(ids);
            driver.setOrderRoute(orderId);
            driver.setStatus(DriverStatus.WORK);
            driverDAO.update(driver);
        }

    }

    private List<Baggage> createBaggageFromTickets(List<Ticket> tickets) {
        List<Baggage> baggages = new ArrayList<>();
        int id = baggageDAO.getMaxId();
        for(Ticket t :tickets) {
            Baggage b = new Baggage();
            id += 1;
            b.setId(id);
            b.setName(t.getName());
            b.setWeight(t.getWeight());
            b.setStatus(BaggageStatus.PRODUCED);
            baggages.add(b);
        }
        return baggages;
    }
}
