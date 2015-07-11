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
    public void testGetAllTrucks() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getAll()).thenReturn(trucks);
        TruckService truckService = new TruckService(mockTruckDAO);

        Assert.assertTrue(truckService.getAllTrucks().size() > 0);
        Mockito.verify(mockTruckDAO, Mockito.atLeastOnce()).getAll();
        Mockito.verify(mockTruckDAO, Mockito.never()).getFreeTrucks();
    }

    @Test
    public void testGetOKTrucks() throws ClassNotFoundException, SQLException {
        TruckService truckService = new TruckService(mockTruckDAO);
        Mockito.when(mockTruckDAO.getTrucksByStatus(TruckStatus.OK)).thenReturn(trucks);
        Assert.assertTrue(truckService.getOKTrucks().size() == 1);
        Mockito.verify(mockTruckDAO, Mockito.atLeastOnce()).getTrucksByStatus(TruckStatus.OK);
    }

    @Test
    public void testGetDefectiveTrucks() throws ClassNotFoundException, SQLException {
        TruckService truckService = new TruckService(mockTruckDAO);
        Mockito.when(mockTruckDAO.getTrucksByStatus(TruckStatus.DEFECTIVE)).thenReturn(trucks);
        Assert.assertTrue(truckService.getDefectiveTrucks().size() == 1);
        Mockito.verify(mockTruckDAO, Mockito.atLeastOnce()).getTrucksByStatus(TruckStatus.DEFECTIVE);
    }


    @Test
    public void testGetFreeTrucks() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getAll()).thenReturn(trucks);
        TruckService truckService = new TruckService(mockTruckDAO);
        Assert.assertTrue(truckService.getFreeTrucks().size() > 0);
    }

    @Test
    public void testGetInOrderTrucks() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getAll()).thenReturn(trucks);
        TruckService truckService = new TruckService(mockTruckDAO);
        truckService.getInOrderTrucks();
        Mockito.verify(mockTruckDAO, Mockito.atLeastOnce()).getAll();
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
    public void testGetTrucksForOrder() throws ClassNotFoundException, SQLException {
        Mockito.when(mockTruckDAO.getTrucksForOrder(4)).thenReturn(trucks);
        TruckService truckService = new TruckService(mockTruckDAO);
        Assert.assertTrue(truckService.getTrucksForOrder(4).size() == 1);
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
