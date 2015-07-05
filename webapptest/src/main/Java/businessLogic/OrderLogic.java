package businessLogic;

import DAO.BaggageDAOImpl;
import DAO.CityDAOImpl;
import DAO.OrderRouteDAOImpl;
import model.Baggage;
import model.City;
import model.OrderRoute;

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
    public OrderLogic(BaggageDAOImpl baggageDAO, OrderRouteDAOImpl orderRouteDAO) {
        this.baggageDAO = baggageDAO;
        this.orderRouteDAO = orderRouteDAO;
        try {
            this.cityDAO = new CityDAOImpl((Class<City>) Class.forName("model.City"));
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<OrderRoute> getRoute(int orderId) {

        return orderRouteDAO.getRouteByOrderId(orderId);
    }

    public List<DriverPageView> getDriverPageView (int orderId) {

        Set<OrderRoute> routes =  getRoute(orderId);
        List<DriverPageView> views  = new ArrayList<>();
        for(OrderRoute route :routes) {
            try {
            Baggage baggage = baggageDAO.getById(route.getBaggage());
            City city = cityDAO.getById(route.getCity());

                DriverPageView view = new DriverPageView(route.getOrder(),
                        city.getName(),
                        route.getBaggage(),
                        route.getType(),
                        baggage,
                        route.getVisitNumber(),
                        route.getIsDone());
                views.add(view);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        sort(views);
        return views;
    }

    public String getTruckId(int orderId) {
        return orderRouteDAO.getTruckId(orderId);
    }

}
