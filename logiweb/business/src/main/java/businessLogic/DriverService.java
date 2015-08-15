package businessLogic;

import DAO.DriverDAO;
import model.City;
import model.Driver;
import model.statuses.DriverStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Driver work business logic
 * full CRUD for driver data base
 * and some addition functions
 */
@Repository
@Transactional
public class DriverService {

    private static Logger logger = Logger.getLogger(DriverService.class);
    private static int MEDUIM_SPEED = 90;
    @Autowired
    private DriverDAO driverDAO;

    public DriverService() {
    }

    public DriverDAO getDriverDAO() {
        return driverDAO;
    }

    public void setDriverDAO(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public DriverService(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public Set<Driver> getFriends(int driverId){

        return  driverDAO.getDriverFriends(driverId);
    }

    public void setDtiverStatus(int driverId, DriverStatus status) throws SQLException {
        Driver driver = driverDAO.getById(driverId);
        driver.setStatus(status);
        driverDAO.update(driver);
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
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Set<Driver> getAllDrivers() throws SQLException {
        List<Driver> driverList = (List<Driver>) driverDAO.getAll();
        Set<Driver> drivers = new HashSet<>(driverList);
        return drivers;
    }

    /**
     * @return set of drivers that don't work
     */
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

    /**
     * Function finds drivers for order
     * @param hours number of hours that order need
     * @param city start city for order
     * @return set of drivers that ready to do this order
     */
    public Set<Driver> getDriversForOrder(int hours, City city) {

        return  driverDAO.getDriversForOrder(city, hours);
    }

    /***
     * Function finds drivers for order
     * @param roadLenght sum of distances between order cities
     * @param city start city for order
     * @return set of drivers that ready to do this order
     */
    public Set<Driver> getFitDrivers(int roadLenght, City city) {
        int hours = getRoadHours(roadLenght);
        return getDriversForOrder(hours, city);
    }

    /**
     * Function converts order road length for hours
     * @param roadLength sum of distances between order cities
     * @return num of hours that needs for order
     */
    public int getRoadHours(int roadLength) {
        return roadLength/MEDUIM_SPEED;
    }

}
