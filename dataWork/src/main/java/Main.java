import DAO.*;
import model.Baggage;
import model.City;
import model.Truck;
import model.statuses.BaggageStatus;
import model.statuses.TruckStatus;

import java.sql.SQLException;


class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");

//        fillCity();
//        fillBaggage();
//        fillTruck();
        System.out.println("All Cities:");
        Factory.getInstance().getCityDAO().printAll();

        System.out.println("City by id:");
        System.out.println(Factory.getInstance().getCityDAO().getById(42));


        System.out.println("All baggages:");
        Factory.getInstance().getBaggageDAO().printAll();


        System.out.println("All trucks");
        Factory.getInstance().getTruckDAO().printAll();
////
//        System.out.println("All drivers");
//        Factory.getInstance().getDriverDAO().printAll();

//        System.out.println("All orders");
//        Factory.getInstance().


        System.out.println("Good bye!");
    }

    static void fillCity() throws SQLException, ClassNotFoundException {
        Factory.getInstance().getCityDAO().add(new City("Moscow"));
        Factory.getInstance().getCityDAO().add(new City("Saint-Petersburg"));
        Factory.getInstance().getCityDAO().add(new City("Berlin"));
    }

    static void fillBaggage() throws SQLException, ClassNotFoundException {
        Factory.getInstance().getBaggageDAO().add(new Baggage(1, "tea", 1200, BaggageStatus.PRODUCED));
        Factory.getInstance().getBaggageDAO().add(new Baggage(2, "beer", 990, BaggageStatus.PRODUCED));
        Factory.getInstance().getBaggageDAO().add(new Baggage(3, "vodka", 100, BaggageStatus.PRODUCED));
        Factory.getInstance().getBaggageDAO().add(new Baggage(4, "heroin", 500, BaggageStatus.PRODUCED));
    }

    static void fillTruck() throws SQLException, ClassNotFoundException {

        Factory.getInstance().getTruckDAO().add(new Truck(
                "AB12345", 8, 1, TruckStatus.DEFECTIVE,
                Factory.getInstance().getCityDAO().getById(42)
        ));
        Factory.getInstance().getTruckDAO().add(new Truck(
                "AB54321", 8, 1, TruckStatus.DEFECTIVE,
                Factory.getInstance().getCityDAO().getById(43)
        ));
        Factory.getInstance().getTruckDAO().add(new Truck(
                "AB65478", 8, 1, TruckStatus.DEFECTIVE,
                Factory.getInstance().getCityDAO().getById(44)
        ));

    }
}
