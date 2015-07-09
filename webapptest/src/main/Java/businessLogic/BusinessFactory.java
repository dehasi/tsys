package businessLogic;

import DAO.*;
import model.*;

/**
 * Created by Rafa on 03.07.2015.
 */
public class BusinessFactory {
    private static UserLogic userLogic = null;
    private static DriverLogic driverLogic = null;
    private static OrderLogic orderLogic = null;
    private static TruckLogic truckLogic = null;
    private static CityLogic cityLogic = null;
    private static MapLogic mapLogic = null;

    private static BusinessFactory instance = null;

    public static synchronized BusinessFactory getInstance(){
        if (instance == null){
            instance = new BusinessFactory();
        }
        return instance;
    }

    public UserLogic getUserLogic() throws ClassNotFoundException {
        if (userLogic == null) {
            userLogic =  new UserLogic(new UserDAOImpl(User.class));
        }
        return userLogic;
    }

    public DriverLogic getDriverLogic() throws ClassNotFoundException {
        if (driverLogic == null) {
            driverLogic =  new DriverLogic(new DriverDAOImpl((Class<Driver>) Class.forName("model.Driver")));
        }
        return driverLogic;
    }

    public OrderLogic getOrderLogic() throws ClassNotFoundException {
        if (orderLogic == null){
            orderLogic =  new OrderLogic(new BaggageDAOImpl((Class<Baggage>) Class.forName("model.Baggage")),
                new OrderRouteDAOImpl((Class<OrderRoute>) Class.forName("model.OrderRoute")));
        }
        return orderLogic;
    }

    public  TruckLogic getTruckLogic() throws ClassNotFoundException {
        if (truckLogic == null){
            truckLogic =  new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
        }
        return truckLogic;
    }

    public CityLogic getCityLogic() throws ClassNotFoundException {
        if(cityLogic == null) {
            cityLogic = new CityLogic(new CityDAOImpl((Class<City>) Class.forName("model.City")));
        }
        return cityLogic;
    }

    public MapLogic getMapLogic() {
        if(mapLogic == null) {
            mapLogic = new MapLogic(new MapDAOImpl(Map.class));
        }
        return mapLogic;
    }
}
