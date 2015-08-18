package businessLogic;

import DAO.TruckDAOImpl;
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


public class TruckServiceTest {

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
    public void testGetDefectiveTrucks() throws ClassNotFoundException, SQLException {
        TruckService truckService = new TruckService(mockTruckDAO);
        Mockito.when(mockTruckDAO.getTrucksByStatus(TruckStatus.DEFECTIVE)).thenReturn(trucks);
        Assert.assertTrue(truckService.getDefectiveTrucks().size() == 1);
        Mockito.verify(mockTruckDAO, Mockito.atLeastOnce()).getTrucksByStatus(TruckStatus.DEFECTIVE);
    }

    @Test
    public void testGetFitTrucks() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getAll()).thenReturn(trucks);
        TruckService truckService = new TruckService(mockTruckDAO);
        City city = new City();
        truckService.getFitTrucks(42, city);
        Mockito.verify(mockTruckDAO, Mockito.times(1)).getFitTrucks(42, city);
        Mockito.verify(mockTruckDAO, Mockito.never()).getAll();
    }

    @Test
    public void testGetTruckById()  {
        TruckService truckService = new TruckService(mockTruckDAO);
        truckService.getTruckById("AA11111");
        Mockito.verify(mockTruckDAO, Mockito.times(1)).getTruckById("AA11111");
    }

    @Test
    public void testAddTruck() throws SQLException {
        TruckService truckService = new TruckService(mockTruckDAO);
        Truck t = new Truck();
        truckService.addTruck(t);
        Mockito.verify(mockTruckDAO, Mockito.times(1)).add(t);
    }


    @Test
    public void testDeleteTruck() throws SQLException {
        TruckService truckService = new TruckService(mockTruckDAO);
        Truck t = new Truck();
        truckService.deleteTruck(t);
        Mockito.verify(mockTruckDAO, Mockito.times(1)).delete(t);
    }

    @Test
    public void testUpdateTruck() throws SQLException {
        TruckService truckService = new TruckService(mockTruckDAO);
        Truck t = new Truck();
        truckService.updateTruck(t);
        Mockito.verify(mockTruckDAO, Mockito.times(1)).update(t);
    }


    @After
    public void cleanUp() {
    }

}
