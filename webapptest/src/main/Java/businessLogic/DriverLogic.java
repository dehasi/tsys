package businessLogic;

import DAO.DriverDAOImpl;
import model.City;
import model.Driver;
import model.statuses.DriverStatus;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 30.06.2015.
 */
public class DriverLogic {
    private DriverDAOImpl driverDAO = null;

    public DriverLogic(DriverDAOImpl driverDAO) {
        this.driverDAO = driverDAO;
    }

    public Set<Driver> getFriends(int driverId){

        return  driverDAO.getDriverFriends(driverId);
    }

    public void setDtiverStatus(long driverId, DriverStatus status){
    }

    public DriverStatus getStatus(int driverId) {
        try {
            return driverDAO.getById(driverId).getStatus();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Driver getById(int id) {
        try {
            return driverDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Driver> getAllDrivers() throws SQLException {

        return new HashSet<>(driverDAO.getAll());
    }

    public Set<Driver> getFreeDrivers() {
        return new HashSet<Driver>(driverDAO.getFreeDrivers());
    }

    public Set<Driver> getInOrderDrivers() {
        try {
            return new HashSet<>(driverDAO.getInOrderDrivers());
        }catch (NullPointerException e) {
            return null;
        }

    }

    public void addNewDriver(Driver driver) throws SQLException {
        driverDAO.add(driver);
    }

    public void deleteDriver(Driver driver) throws SQLException {
        driverDAO.delete(driver);
    }
    public void updateDriver(Driver driver) throws SQLException {
        driverDAO.update(driver);
    }

    public Set<Driver> getDriversForOrder(int hours, City city) {
        return  driverDAO.getDriversForOrder(city, hours);
    }
}
