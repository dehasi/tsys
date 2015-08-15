package Controlers;

import businessLogic.*;
import com.google.gson.Gson;
import model.Driver;
import model.statuses.BaggageStatus;
import model.statuses.DriverStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * utils for rest requests
 */
@Component
public class DriverUtils {

    Logger logger = Logger.getLogger(DriverUtils.class);

    @Autowired
    DriverService driverService;
    @Autowired
    OrderService orderService;

    public ResponseEntity<String> changeDriverStatus( String id,String status ){

        try {
            int driverId = Integer.parseInt(id);
            DriverStatus driverStatus = DriverStatus.fromString(status);
            driverService.setDtiverStatus(driverId, driverStatus);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> changeOrderStatus(String id,String status) {

        try {
            int baggageId = Integer.parseInt(id);
            BaggageStatus baggageStatus = BaggageStatus.fromString(status);
            orderService.changeBaggageStatus(baggageId, baggageStatus);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public String getDriverViewJSON(String iD) {
        DriverView view = getDriverView(iD);
        Gson gson = new Gson();
        Driver d = view.getDriver();
        String res;
        try {
            res = gson.toJson(view);
        } catch (Exception ignore) {
            res = view.toString();
        }
        return res;
    }
    public DriverView getDriverView(String iD) {
        int id = Integer.parseInt(iD);
        Driver driver = driverService.getById(id);

        if(driver == null)
            return null;

        DriverView driverView = new DriverView();
        driverView.setDriver(driver);

        Integer orderId = driver.getOrderRoute();

        if(orderId != null) {
            List<Driver> friends = new ArrayList<>();
            friends.addAll(driverService.getFriends(id));
            friends.remove(driver);

            List<OrderView> route = orderService.getOrderView(orderId);

            driverView.setFriends(friends);
            driverView.setRoute(route);

            String truckId = orderService.getTruckIdByOrder(orderId);
            driverView.setOrderId(orderId);
            driverView.setTruckId(truckId);
        }
        return driverView;
    }
}
