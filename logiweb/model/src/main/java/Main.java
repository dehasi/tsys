import DAO.BaggageDAOImpl;
import DAO.CityDAOImpl;
import com.google.gson.Gson;
import model.Baggage;
import model.Driver;
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
        BaggageDAOImpl cargoDao = ctx.getBean(BaggageDAOImpl.class);
        System.out.println((cargoDao).getMaxId());
        System.out.println((cargoDao).getById(1));
//        System.out.println((cargoDao).getAll());
        System.out.println((cargoDao).getById(1));
        Baggage b = cargoDao.getById(1);
//        b.setStatus(BaggageStatus.DONE);
//        cargoDao.update(b);

        Baggage baggage = new Baggage();
        baggage.setId(42);
        baggage.setStatus(BaggageStatus.DONE);
        baggage.setName("PIVO");
        baggage.setWeight(120);
        cargoDao.delete(b);
        System.out.println((cargoDao).getById(1));
//        System.out.println((cargoDao).getBaggagesByStatus(BaggageStatus.PRODUCED));
//        System.out.println((cargoDao).getAll());
        System.out.println((cargoDao).getAll());
        System.out.println("Good bye!");


    }

}
