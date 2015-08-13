package DAO;

import model.OrderRoute;
import model.statuses.DoneStatus;

import java.util.Set;

/**
 * Created by Rafa on 14.08.2015.
 */
public interface OrderRouteDAO extends GeneticDAO<OrderRoute> {

    Set<OrderRoute> getOrdersByStaus(DoneStatus status);

    Set<OrderRoute> getRouteByOrderId(int orderId);

    String getTruckId(int orderId);

    OrderRoute getOrderByTruckId(String truckId);

    Set<Integer> getAllOrderIds();

    int getOrderStatus(int orderId);

    int getMaxId();

    Set<OrderRoute> getRoutesByBaggageId(int baggageId);
}
