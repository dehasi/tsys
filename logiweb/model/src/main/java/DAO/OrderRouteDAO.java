package DAO;

import model.OrderRoute;
import model.Truck;
import model.statuses.DoneStatus;

import java.util.Set;

/**
 * Interface for working with orders and routes
 */
public interface OrderRouteDAO extends GeneticDAO<OrderRoute> {

    Set<OrderRoute> getOrdersByStatus(DoneStatus status);

    Set<OrderRoute> getRouteByOrderId(int orderId);

    String getTruckId(int orderId);

    OrderRoute getOrderByTruck(Truck truck);

    Set<Integer> getAllOrderIds();

    int getOrderStatus(int orderId);

    int getMaxId();

    Set<OrderRoute> getRoutesByBaggageId(int baggageId);
}
