import DAO.*;
import com.google.gson.Gson;
import model.*;
import model.statuses.BaggageStatus;
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
//        BaggageDAO baggageDAO = ctx.getBean(BaggageDAO.class);
//        Baggage banana = baggageDAO.getById(1);
//
//        CityDAO cityDAO = ctx.getBean(CityDAO.class);
//        System.out.println(cityDAO.getAll());
//
//        City moscow = cityDAO.getById(1);
//        System.out.println(moscow);
//        City spb = cityDAO.getById(2);
//        System.out.println(spb);
//
//
//        TruckDAO truckDAO = ctx.getBean(TruckDAO.class);
//        System.out.println(truckDAO.getAll());
//
//        Truck truck = truckDAO.getTruckById("AB12345");
//        System.out.println(truck);
//
//        OrderRouteDAO routeDAO = ctx.getBean(OrderRouteDAO.class);
//        System.out.println(routeDAO.getAll());

//        OrderRoute route1 = new OrderRoute();
//        OrderRoute route2 = new OrderRoute();
//
//        route1.setOrder(1);
//        route1.setCity(moscow);
//        route1.setBaggage(banana);
//        route1.setLoadStatus(LoadStatus.LOADING);
//        route1.setIsBaggageDone(DoneStatus.NOT_DONE);
//        route1.setVisitNumber(1);
//        route1.setOrderStatus(DoneStatus.NOT_DONE);
//        route1.setTruck(truck);
//
//
//        route2.setOrder(1);
//        route2.setCity(spb);
//        route2.setBaggage(banana);
//        route2.setLoadStatus(LoadStatus.UNLOADING);
//        route2.setIsBaggageDone(DoneStatus.NOT_DONE);
//        route2.setVisitNumber(2);
//        route2.setOrderStatus(DoneStatus.NOT_DONE);
//        route2.setTruck(truck);
//
//        routeDAO.add(route1);
//        routeDAO.add(route2);
//
//        System.out.println(routeDAO.getAll());
        System.err.println("--------------------------------------------------");
        DriverDAO driverDAO = ctx.getBean(DriverDAO.class);
//        System.out.println(driverDAO.getAll());

        System.out.println("Good bye!");
    }

}
