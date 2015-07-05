package businessLogic;

import DAO.CityDAOImpl;
import model.City;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rafa on 05.07.2015.
 */
public class CityLogic {
    private CityDAOImpl cityDAO = null;

    public CityLogic(CityDAOImpl cityDAO) {
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
