package businessLogic;

import DAO.*;
import model.Baggage;
import model.City;
import model.OrderRoute;
import model.Truck;
import model.statuses.BaggageStatus;
import model.statuses.DoneStatus;
import model.statuses.DriverStatus;
import model.statuses.LoadStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

import static java.util.Collections.sort;

/**
 * Class represents business logic for order work
 * Creating oeder and return all statuses of order in
 * different views
 */
@Service
public class OrderService {

    private static Logger logger = Logger.getLogger(OrderService.class);
    @Autowired
    BaggageDAO baggageDAO;
    @Autowired
    OrderRouteDAO orderRouteDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    MapDAO mapDAO;
    @Autowired
    DriverDAO driverDAO;
    @Autowired
    TruckDAO truckDAO;

    /**
     * Constructor. creates instance of class
     * @param baggageDAO DAO for baggage working
     * @param orderRouteDAO DAO for order work
     * @param cityDAO DAO for city work
     * @param mapDAO DAO for map work
     * @param driverDAO DAO for driver work
     */
    public OrderService(BaggageDAO baggageDAO, OrderRouteDAO orderRouteDAO,
                        CityDAO cityDAO, MapDAO mapDAO, DriverDAO driverDAO, TruckDAO truckDAO) {
        this.baggageDAO = baggageDAO;
        this.orderRouteDAO = orderRouteDAO;
        this.cityDAO = cityDAO;
        this.mapDAO = mapDAO;
        this.driverDAO = driverDAO;
        this.truckDAO = truckDAO;

    }

    public OrderService() {
    }

    public Set<OrderRoute> getRoute(int orderId) {

        return orderRouteDAO.getRouteByOrderId(orderId);
    }

    public List<OrderView> getOrderView(int orderId) {

        Set<OrderRoute> routes =  getRoute(orderId);
        List<OrderView> views  = new ArrayList<>();
        for(OrderRoute route :routes) {
            Baggage baggage = route.getBaggage();
            City city = route.getCity();

                OrderView view = new OrderView(route.getOrderId(),
                        city,
                        route.getBaggage(),
                        route.getLoadStatus() ,
                        route.getVisitNumber(),
                        route.getIsBaggageDone(),
                        route.getTruck());
                views.add(view);
        }
        sort(views);
        return views;
    }



    public String getTruckIdByOrder(int orderId) {

        return orderRouteDAO.getTruckId(orderId);
    }

    public Integer getOrderIdByTruck(Truck truck) {
        OrderRoute route =  orderRouteDAO.getOrderByTruck(truck);
        if(route != null) {
            return route.getOrderId();
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
            if(o.getIsDone() == DoneStatus.NOT_DONE){
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
        int orderId = orderRouteDAO.getMaxOrderId() + 1;
        int oId = orderRouteDAO.getMaxId() + 1;
        List<OrderRoute> routes = new ArrayList<>();

        int baxIndex = 0;
        int visitNumber = 0;
        for (Ticket t : tickets) {
            OrderRoute oLoad = new OrderRoute();
            OrderRoute oUnLoad = new OrderRoute();

            oLoad.setId(++oId);
            oLoad.setOrderId(orderId);
            oLoad.setCity(cityDAO.getById(t.loadId));
            oLoad.setBaggage(baggages.get(baxIndex));
            oLoad.setIsBaggageDone(DoneStatus.NOT_DONE);
            oLoad.setOrderStatus(DoneStatus.NOT_DONE);
            oLoad.setLoadStatus(LoadStatus.LOADING); // load
            oLoad.setVisitNumber(++visitNumber);
            oLoad.setTruck(truckDAO.getTruckById(truckId));

            oUnLoad.setId(++oId);
            oUnLoad.setOrderId(orderId);
            oUnLoad.setCity(cityDAO.getById(t.unloadId));
            oUnLoad.setBaggage(baggages.get(baxIndex));
            oUnLoad.setIsBaggageDone(DoneStatus.NOT_DONE);
            oUnLoad.setOrderStatus(DoneStatus.NOT_DONE);
            oUnLoad.setLoadStatus(LoadStatus.UNLOADING);
            oUnLoad.setVisitNumber(++visitNumber);
            oUnLoad.setTruck(truckDAO.getTruckById(truckId));
            routes.add(oLoad);
            routes.add(oUnLoad);
            ++baxIndex;
        }

        logger.info("begin add routes");
        for (OrderRoute r : routes) {
            orderRouteDAO.add(r);
        }
        logger.info("begin updete driver status");
        for(int ids : driverIds){
            model.Driver driver = driverDAO.getById(ids);
            driver.setOrderRoute(orderId);
            driver.setStatus(DriverStatus.WORK);
            driverDAO.update(driver);
        }
        logger.info("Order created");
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

    //TODO: fix
    public void changeBaggageStatus (int baggageId, BaggageStatus status) throws SQLException {
        Baggage baggage = baggageDAO.getById(baggageId);
        baggage.setStatus(status);
        baggageDAO.update(baggage);
        Set<OrderRoute> routes = orderRouteDAO.getRoutesByBaggageId(baggageId);
//PRODUCED(0), SHIPPED(1), DONE(2);
        switch (status) {
            case DONE:{
                int orderId = -1;
                for (OrderRoute or : routes) {
//                    if (or.getLoadStatus() == LoadStatus.UNLOADING) {
                        or.setIsBaggageDone(DoneStatus.DONE);
                        orderRouteDAO.update(or);
                        orderId = or.getOrderId();
//                        break;
//                    }
                }

                if (orderId != -1) {
                    Set<OrderRoute> routeSet = getRoute(orderId);
                    if(isOrderDone(routeSet)) {
                        for (OrderRoute r : routeSet) {
                            r.setIsBaggageDone(DoneStatus.DONE);
                            orderRouteDAO.update(r);
                        }
                    }
                }
                break;
            }
            case PRODUCED: {
                for (OrderRoute or : routes) {
                    if (or.getLoadStatus() == LoadStatus.LOADING) {
                        or.setIsBaggageDone(DoneStatus.DONE);
                        orderRouteDAO.update(or);
                        break;
                    }
                }
                break;
            }
            case SHIPPED: {

                break;
            }
        }
    }

    private boolean isOrderDone(Set<OrderRoute> routes) {
        for(OrderRoute r : routes) {
            if (r.getIsBaggageDone() == DoneStatus.DONE) {
                return false;
            }
        }
        return true;
    }


    public BaggageDAO getBaggageDAO() {
        return baggageDAO;
    }

    public void setBaggageDAO(BaggageDAO baggageDAO) {
        this.baggageDAO = baggageDAO;
    }

    public OrderRouteDAO getOrderRouteDAO() {
        return orderRouteDAO;
    }

    public void setOrderRouteDAO(OrderRouteDAO orderRouteDAO) {
        this.orderRouteDAO = orderRouteDAO;
    }

    public CityDAO getCityDAO() {
        return cityDAO;
    }

    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public MapDAO getMapDAO() {
        return mapDAO;
    }

    public void setMapDAO(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    public DriverDAO getDriverDAO() {
        return driverDAO;
    }

    public void setDriverDAO(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }
}
