package businessLogic;

import DAO.DriverDAOImpl;
import model.City;
import model.Driver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Set;

/**
 * Created by Rafa on 11.07.2015.
 */
public class DriverServiceTest {
    @Mock
    DriverDAOImpl mockDriverDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFitDriversTest() {
        DriverService driverService = new DriverService(mockDriverDAO);
        City city = new City();
        int hours = 10;
        int roadLength = 900;
        driverService.getFitDrivers(roadLength, city);
        Mockito.verify(mockDriverDAO, Mockito.atLeastOnce()).getDriversForOrder(city, hours);
    }

    @Test
    public void calculateRoadHoursTest() {
        DriverService driverService = new DriverService(mockDriverDAO);
        int h  = driverService.getRoadHours(900);
        Assert.assertEquals(h,10);
    }
}
