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
            baggageDAO = new BaggageDAOImpl((Class<Baggage>) Class.forName("model.Baggage"));
        }
        return baggageDAO;
    }

    public CityDAOImpl getCityDAO() throws ClassNotFoundException {
        if (cityDAO == null) {
            cityDAO = new CityDAOImpl((Class<City>) Class.forName("model.City"));
        }
        return cityDAO;
    }

    public TruckDAOImpl getTruckDAO() throws ClassNotFoundException {
        if (truckDAO == null) {
            truckDAO = new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck"));
        }
        return truckDAO;
    }

    public DriverDAOImpl getDriverDAO() throws ClassNotFoundException {
        if (driverDAO == null) {
            driverDAO = new DriverDAOImpl((Class<Driver>) Class.forName("model.Driver"));
        }
        return driverDAO;
    }

}