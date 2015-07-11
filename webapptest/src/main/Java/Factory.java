import DAO.*;
import model.Baggage;
import model.City;
import model.Driver;
import model.Truck;

public class Factory {

    private static BaggageDAOImpl baggageDAO= null;
    private static CityDAOImpl cityDAO = null;
    private static TruckDAOImpl truckDAO = null;
    private static DriverDAOImpl driverDAO = null;

    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public BaggageDAOImpl getBaggageDAO() throws ClassNotFoundException {
        if (baggageDAO == null){
            baggageDAO = new BaggageDAOImpl(Baggage.class);
        }
        return baggageDAO;
    }

    public CityDAOImpl getCityDAO() throws ClassNotFoundException {
        if (cityDAO == null) {
            cityDAO = new CityDAOImpl(City.class);
        }
        return cityDAO;
    }

    public TruckDAOImpl getTruckDAO() throws ClassNotFoundException {
        if (truckDAO == null) {
            truckDAO = new TruckDAOImpl(Truck.class);
        }
        return truckDAO;
    }

    public DriverDAOImpl getDriverDAO() throws ClassNotFoundException {
        if (driverDAO == null) {
            driverDAO = new DriverDAOImpl(Driver.class);
        }
        return driverDAO;
    }

}