import DAO.*;
import model.City;
import model.OrderRoute;
import model.statuses.DoneStatus;
import model.statuses.LoadStatus;
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
        BaggageDAO baggageDAO = ctx.getBean(BaggageDAO.class);
        City city = cityDAO.getById(1);

//        System.out.println(truckDAO.getFitTrucks(8, city));

        OrderRoute oLoad = new OrderRoute();
        //TODO: fix this shit [done?]
        oLoad.setOrder(4);
        oLoad.setCity(cityDAO.getById(2));
        oLoad.setBaggage(baggageDAO.getById(4));
        oLoad.setIsBaggageDone(DoneStatus.NOT_DONE);
        oLoad.setOrderStatus(DoneStatus.NOT_DONE);
        oLoad.setLoadStatus(LoadStatus.LOADING); // load
        oLoad.setVisitNumber(1);
        oLoad.setTruck(truckDAO.getTruckById("AB12345"));
        routeDAO.add(oLoad);
        System.out.println(routeDAO.getAll());
        System.out.println("Good bye!");
    }

}
