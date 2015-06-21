package DAO;

import model.Cargo;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Rafa on 19.06.2015.
 */
public interface CargoDAO {
    void addCargo(Cargo cargo) throws SQLException;
    void updateCargo(Long id, Cargo cargo) throws SQLException;
    Cargo getCargoById(Long id) throws SQLException;
    Collection getAllCargos() throws SQLException;
    void deleteCargo(Cargo cargo) throws SQLException;
}
