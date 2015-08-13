import DAO.*;
import com.google.gson.Gson;
import model.Baggage;
import model.Driver;
import model.Truck;
import model.statuses.BaggageStatus;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;


class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");
//
//        Driver d = new Driver();
//        d.setId(42);
//        d.setName("Vasa");
//        d.setLastName("Pupkin");
//
//        Gson gson = new Gson();
//
//        String s = gson.toJson(d);




        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        System.out.println(ctx);
        BaggageDAO cargoDao = ctx.getBean(BaggageDAO.class);
//        System.out.println((cargoDao).getMaxId());
//        System.out.println((cargoDao).getById(2));
//        System.out.println((cargoDao).getAll());
//        System.out.println((cargoDao).getById(2));
//        Baggage b = cargoDao.getById(2);
//        b.setStatus(BaggageStatus.DONE);
//        cargoDao.update(b);
//
//        Baggage baggage = new Baggage();
//        baggage.setId(42);
//        baggage.setStatus(BaggageStatus.DONE);
//        baggage.setName("PIVO");
//        baggage.setWeight(120);
//        cargoDao.add(baggage);
        System.out.println("After delete:");
        System.out.println((cargoDao).getById(2));
//        System.out.println((cargoDao).getBaggagesByStatus(BaggageStatus.PRODUCED));
//        System.out.println((cargoDao).getAll());
        System.out.println((cargoDao).getAll());


//        DriverDAOImpl driverDAO = ctx.getBean(DriverDAOImpl.class);
//        System.out.println(driverDAO.getAll());
//
//        TruckDAOImpl truckDAO = ctx.getBean(TruckDAOImpl.class);
//        System.out.println(truckDAO.getAll());

        TruckDAO truckDAO = ctx.getBean(TruckDAO.class);
        System.out.println(truckDAO.getAll());
        System.out.println("Good bye!");


    }

}
