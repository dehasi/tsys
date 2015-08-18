import DAO.*;
import model.City;
import model.OrderRoute;
import model.statuses.DoneStatus;
import model.statuses.LoadStatus;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.sql.SQLException;


class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        System.out.println(ctx);

        LocalContainerEntityManagerFactoryBean bean = (LocalContainerEntityManagerFactoryBean) ctx.getBean("entityManagerFactory");


        Class<? extends EntityManager> entityManagerInterface = bean.getEntityManagerInterface();

        System.out.println(entityManagerInterface);

        OrderRouteDAO routeDAO = ctx.getBean(OrderRouteDAO.class);
        TruckDAO truckDAO = ctx.getBean(TruckDAO.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        CityDAO cityDAO = ctx.getBean(CityDAO.class);
        BaggageDAO baggageDAO = ctx.getBean(BaggageDAO.class);
        City city = cityDAO.getById(1);
        DriverDAO driverDAO = ctx.getBean(DriverDAO.class);

        System.out.println(driverDAO.getById(1));
//        System.out.println(driverDAO.getAll());
        System.out.println("Good bye!");


        logger.error("lol");

    }

}
