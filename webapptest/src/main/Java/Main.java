import DAO.*;
import businessLogic.TruckLogic;
import businessLogic.UserLogic;
import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.Set;


class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");
        HibernateUtil.getSessionFactory();
       TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl(Truck.class));
        City city = new City();
        city.setId(1);
        city.setName("Moscow");
        Set<Truck> trucks = truckLogic.getFitTrucks(2,city);
        System.out.println(trucks);
        System.out.println("Good bye!");

    }

}
