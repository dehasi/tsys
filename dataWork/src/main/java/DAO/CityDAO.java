package DAO;

import java.sql.SQLException;
import java.util.Collection;
import model.City;

public interface CityDAO {
    void addCity(City city) throws SQLException;
    void updateCity(Long id, City city) throws SQLException;
    City getCityById(Long id) throws SQLException;
    Collection getAllCities() throws SQLException;
    void deleteCity(City city) throws SQLException;
}
