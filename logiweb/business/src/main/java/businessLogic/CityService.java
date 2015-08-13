package businessLogic;

import DAO.CityDAOImpl;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Business logic for city work
 * just only getters and setters
 */
@Service
public class CityService {
    @Autowired
    private CityDAOImpl cityDAO;

    public CityService(CityDAOImpl cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Transactional
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

    public CityService() {
    }

    public CityDAOImpl getCityDAO() {
        return cityDAO;
    }

    public void setCityDAO(CityDAOImpl cityDAO) {
        this.cityDAO = cityDAO;
    }
}
