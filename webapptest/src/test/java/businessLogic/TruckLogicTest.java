package businessLogic;

/**
 * Created by Rafa on 28.06.2015.
 */
import DAO.TruckDAOImpl;
import jdk.nashorn.internal.ir.annotations.Ignore;
import model.City;
import model.Truck;
import model.statuses.TruckStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class TruckLogicTest {

    @Mock
    private TruckDAOImpl mockTruckDAO;
    private Set<Truck> trucks;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trucks = new HashSet<>();
        trucks.add(new Truck());
    }

    @Test
    public void testGetAllTrucks() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getAll()).thenReturn(trucks);
        TruckLogic truckLogic = new TruckLogic(mockTruckDAO);

        Assert.assertTrue(truckLogic.getAllTrucks().size()> 0);
        Mockito.verify(mockTruckDAO, Mockito.atLeastOnce()).getAll();
        Mockito.verify(mockTruckDAO, Mockito.never()).getFreeTrucks();
    }

    @Test
    public void testGetOKTrucks() throws ClassNotFoundException, SQLException {
        TruckLogic truckLogic = new TruckLogic(mockTruckDAO);
        Mockito.when(mockTruckDAO.getTrucksByStatus(TruckStatus.OK)).thenReturn(trucks);
        Assert.assertTrue(truckLogic.getOKTrucks().size() == 1);
    }

    @Test
    @Ignore
    public void testGetFreeTrucks() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getAll()).thenReturn(trucks);
        TruckLogic truckLogic = new TruckLogic(mockTruckDAO);
        Assert.assertTrue(truckLogic.getFreeTrucks().size() > 0);
    }

    @Test
    public void testGetFitTrucks() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getAll()).thenReturn(trucks);
        TruckLogic truckLogic = new TruckLogic(mockTruckDAO);
        City city = new City();
        truckLogic.getFitTrucks(42, city);
        Mockito.verify(mockTruckDAO, Mockito.atLeastOnce()).getFitTrucks(42,city);
        Mockito.verify(mockTruckDAO, Mockito.never()).getAll();
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
