package DAO;

import model.City;
import model.Driver;
import model.statuses.DriverStatus;

import java.util.Set;

/**
 * Created by Rafa on 14.08.2015.
 */
public interface DriverDAO extends GeneticDAO<Driver> {
    Set<Driver> getDriversByCity(City city);

    Set<Driver> getDriversByStatus(DriverStatus status);

    Set<Driver> getDriversByOrderId(Long orderId);

    Set<Driver> getDriversForOrder(City city, int hoursWorked);

    Set<Driver> getDriverFriends(int id);

    Set<Driver> getInOrderDrivers();

    Set<Driver> getFreeDrivers();
}
