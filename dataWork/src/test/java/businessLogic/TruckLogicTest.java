package businessLogic;

/**
 * Created by Rafa on 28.06.2015.
 */
import DAO.TruckDAOImpl;
import model.Truck;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;


public class TruckLogicTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllTrucks() throws ClassNotFoundException, SQLException {
        TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
        Assert.assertTrue(truckLogic.getAllTrucks().size() > 0);
    }

    @After
    public void cleanUp() {
    }


}
