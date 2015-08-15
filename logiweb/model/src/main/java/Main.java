import DAO.*;
import model.City;
import model.statuses.DoneStatus;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;


class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        System.out.println(ctx);

        OrderRouteDAO routeDAO = ctx.getBean(OrderRouteDAO.class);
        TruckDAO truckDAO = ctx.getBean(TruckDAO.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        CityDAO cityDAO = ctx.getBean(CityDAO.class);

        City city = cityDAO.getById(1);

        System.out.println(truckDAO.getFitTrucks(8, city));

        System.out.println("Good bye!");
    }

}
