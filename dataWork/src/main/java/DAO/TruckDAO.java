package DAO;

import model.Truck;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Rafa on 20.06.2015.
 */
public interface TruckDAO {
    void addTruck(Truck truck) throws SQLException;
    void updateTruck(Long id, Truck truck) throws SQLException;
    Truck getTruckById(String id) throws SQLException;
    Collection getAllTrucks() throws SQLException;
    void deleteTruck(Truck truck) throws SQLException;
}
