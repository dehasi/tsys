package businessLogic;

import DAO.CityDAOImpl;
import model.City;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Business logic for city work
 * just only getters and setters
 */
public class CityService {
    private CityDAOImpl cityDAO = null;

    public CityService(CityDAOImpl cityDAO) {
        this.cityDAO = cityDAO;
    }

    public Set<City> getAllCities() throws SQLException {
        return new HashSet<>( cityDAO.getAll());
    }
    public String getNameById(int id) throws SQLException {
        City city = cityDAO.getById(id);
        return city.getName();
    }

    public City getCityById(int id) throws SQLException {
        City city = cityDAO.getById(id);
        return city;
    }
}
