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

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        System.out.println(ctx);
        BaggageDAO cargoDao = ctx.getBean(BaggageDAO.class);

        TruckDAO truckDAO = ctx.getBean(TruckDAO.class);
        System.out.println(truckDAO.getAll());

        OrderRouteDAO routeDAO = ctx.getBean(OrderRouteDAO.class);
        System.out.println(routeDAO.getAll());

        System.out.println("Good bye!");
    }

}
