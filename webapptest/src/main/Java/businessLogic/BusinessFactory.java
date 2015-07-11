package businessLogic;

import DAO.*;
import model.*;

/**
 * Class represents factory for creating DAO objects
 */
public class BusinessFactory {
    private static UserService userService = null;
    private static DriverLogic driverLogic = null;
    private static OrderService orderService = null;
    private static TruckService truckService = null;
    private static CityService cityService = null;
    private static MapService mapService = null;

    private static BusinessFactory instance = null;

    public static synchronized BusinessFactory getInstance(){
        if (instance == null){
            instance = new BusinessFactory();
        }
        return instance;
    }

    public UserService getUserLogic() {
        if (userService == null) {
            userService =  new UserService(new UserDAOImpl(User.class));
        }
        return userService;
    }

    public DriverLogic getDriverLogic() {
        if (driverLogic == null) {
            driverLogic =  new DriverLogic(new DriverDAOImpl(Driver.class));
        }
        return driverLogic;
    }

    public OrderService getOrderLogic() {
        if (orderService == null){
            orderService =  new OrderService(new BaggageDAOImpl(Baggage.class),
                new OrderRouteDAOImpl(OrderRoute.class),
                    new CityDAOImpl(City.class),
                    new MapDAOImpl(model.Map.class),
                    new DriverDAOImpl(model.Driver.class)
            );
        }
        return orderService;
    }

    public TruckService getTruckLogic() {
        if (truckService == null){
            truckService =  new TruckService(new TruckDAOImpl(Truck.class));
        }
        return truckService;
    }

    public CityService getCityLogic() {
        if(cityService == null) {
            cityService = new CityService(new CityDAOImpl(City.class));
        }
        return cityService;
    }

    public MapService getMapLogic() {
        if(mapService == null) {
            mapService = new MapService(new MapDAOImpl(Map.class));
        }
        return mapService;
    }
}
