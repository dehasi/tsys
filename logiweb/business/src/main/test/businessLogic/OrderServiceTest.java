package businessLogic;

import DAO.*;
import model.*;
import model.statuses.BaggageStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class OrderServiceTest {
    @Mock
    private TruckDAOImpl mockTruckDAO;
    @Mock
    private DriverDAOImpl mockDriverDAO;
    @Mock
    private CityDAOImpl mockCityDAO;
    @Mock
    private BaggageDAOImpl mockBaggageDAO;
    @Mock
    private OrderRouteDAOImpl mockRouteDAO;
    @Mock
    private MapDAOImpl mockMapDAO;

    private  Set<OrderRoute> routes;
    private Baggage baggage;
    private City city;
    private Driver driver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        routes = new HashSet<>();

        city = new City();
        city.setName("sbp");
        city.setId(1);
        baggage = new Baggage();
        baggage.setId(1);
        baggage.setWeight(2);
        baggage.setName("cacao");
        baggage.setStatus(BaggageStatus.PRODUCED);
        OrderRoute route = new OrderRoute();
        route.setCity(city);
        route.setBaggage(baggage);

        routes.add(route);

        driver = new Driver();
        driver.setCity(city);
        driver.setId(1);
    }


    @Test
    public void getOrderViewTest() throws SQLException {
        OrderService service = new OrderService(mockBaggageDAO, mockRouteDAO, mockCityDAO, mockMapDAO,mockDriverDAO, mockTruckDAO);
        Mockito.when(mockRouteDAO.getRouteByOrderId(1)).thenReturn(routes);
        Mockito.when(mockCityDAO.getById(1)).thenReturn(this.city);
        Mockito.when(mockBaggageDAO.getById(1)).thenReturn(this.baggage);
        service.getOrderView(1);
    }

    @Test
    public void createOrderTest() throws SQLException {
        OrderService service = new OrderService(mockBaggageDAO, mockRouteDAO, mockCityDAO, mockMapDAO,mockDriverDAO, mockTruckDAO);
        Ticket ticket = new Ticket("dog", 1, 1, 1);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        List<Integer> driverIds = new ArrayList<>();
        driverIds.add(1);
        Truck truck = new Truck();//
        String truckId  = "DD55555";

        Mockito.when(mockTruckDAO.getTruckById("DD55555")).thenReturn(truck);
        Mockito.when(mockRouteDAO.getRouteByOrderId(1)).thenReturn(this.routes);
        Mockito.when(mockDriverDAO.getById(1)).thenReturn(this.driver);
        service.createOrder(tickets, driverIds, truckId);

    }


}
