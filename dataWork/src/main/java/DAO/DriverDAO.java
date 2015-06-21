package DAO;

import model.Driver;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Rafa on 21.06.2015.
 */
public interface DriverDAO {
    void addDriver (Driver driver) throws SQLException;
    void updateDriver(Long id, Driver driver) throws SQLException;
    Driver getDriverById(String id) throws SQLException;
    Collection getAllDrivers() throws SQLException;
    void deleteDriver(Driver driver) throws SQLException;
}
