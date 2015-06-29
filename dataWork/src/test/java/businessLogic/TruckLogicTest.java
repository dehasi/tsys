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

    @Test
    public void testGetOKTrucks() throws ClassNotFoundException, SQLException {
        TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
        Assert.assertTrue(truckLogic.getOKTrucks().size() == 2);
    }

    @Test
    public void testGetFreeTrucks() throws ClassNotFoundException, SQLException {
        TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
//        Assert.assertTrue(truckLogic.getFreeTrucks().size() == 0);
        Assert.assertNull(truckLogic.getFreeTrucks());
    }

    @Test
    public void testGetFitTrucks() throws ClassNotFoundException, SQLException {
        TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
        Assert.assertTrue(truckLogic.getFitTrucks(6).size() == 2);
    }

    @Test
    public void testGetTrucksForOrger() throws ClassNotFoundException, SQLException {
        TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
        Assert.assertTrue(truckLogic.getTrucksForOrder(6).size() == 1);
    }
    @After
    public void cleanUp() {
    }


}
