package businessLogic;

import DAO.DriverDAOImpl;
import model.Driver;
import model.statuses.DriverStatus;

import java.sql.SQLException;
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
}
