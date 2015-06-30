package businessLogic;

import DAO.DriverDAOImpl;
import model.Driver;
import model.statuses.DriverStatus;

import java.util.List;

/**
 * Created by Rafa on 30.06.2015.
 */
public class DriverLogic {
    private DriverDAOImpl driverDAO = null;

    public DriverLogic(DriverDAOImpl driverDAO) {
        this.driverDAO = driverDAO;
    }

    public List<Driver> getFriends(long driverId){
        return null;
    }

    public void setDtiverStatus(long driverId, DriverStatus status){
    }

    public DriverStatus getStatus(long driverId) {
        return null;
    }
}
