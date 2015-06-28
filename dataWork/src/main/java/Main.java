import DAO.*;
import businessLogic.TruckLogic;
import model.Baggage;
import model.City;
import model.Truck;
import model.statuses.BaggageStatus;
import model.statuses.TruckStatus;

import java.sql.SQLException;
import java.util.Set;


class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");

        TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));

        Set<Truck> trucks =  truckLogic.getAllTrucks();
        System.out.println(trucks);
        System.out.println("Good bye!");
    }

//        System.out.println("All Cities:");
//        Factory.getInstance().getCityDAO().printAll();
//
//        System.out.println("City by id:");
//        System.out.println(Factory.getInstance().getCityDAO().getById(42));
//
//        System.out.println("All baggages:");
//        Factory.getInstance().getBaggageDAO().printAll();
//
//        System.out.println("All trucks");
//        Factory.getInstance().getTruckDAO().printAll();
//
//        System.out.println("All drivers");
//        Factory.getInstance().getDriverDAO().printAll();

}
