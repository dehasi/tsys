import DAO.*;

public class Factory {

    private static CargoDAO cargoDAO = null;
    private static CityDAO cityDAO = null;
    private static TruckDAO truckDAO = null;
    private static DriverDAO driverDAO = null;

    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public CargoDAO getCargoDAO(){
        if (cargoDAO == null){
            cargoDAO = new CargoDAOImpl();
        }
        return cargoDAO;
    }

    public CityDAO getCityDAO() {
        if (cityDAO == null) {
            cityDAO = new CityDAOImpl();
        }
        return cityDAO;
    }

    public TruckDAO getTruckDAO() {
        if (truckDAO == null) {
            truckDAO = new TruckDAOImpl();
        }
        return truckDAO;
    }

    public DriverDAO getDriverDAO() {
        if (driverDAO == null) {
            driverDAO = new DriverDAOImpl();
        }
        return driverDAO;
    }

}