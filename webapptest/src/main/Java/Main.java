import DAO.*;
import businessLogic.TruckLogic;
import businessLogic.UserLogic;
import model.*;
import model.statuses.BaggageStatus;
import model.statuses.TruckStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.Set;


class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");

        TruckLogic truckLogic = new TruckLogic(new TruckDAOImpl((Class<Truck>) Class.forName("model.Truck")));
        Set<Truck> trucks =  truckLogic.getFitTrucks(6);
        System.out.println(trucks);

        UserLogic userLogic = new UserLogic(new UserDAOImpl((Class<User>) Class.forName("model.User")));
        userLogic.isValidUser("", "");
        userLogic.isValidUser("login", "password");
        userLogic.isValidUser("login", "password2");

        System.out.println("\n\n\n-------------\n\n\n");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("Driver.getDriverFriends")
                .setString("did", String.valueOf(1));

        System.out.println(query.list());

         query = session.getNamedQuery("OrderRoute.getRoute")
                .setString("id", String.valueOf(1));

        System.out.println(query.list());

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