package classes.data;

import java.util.List;

/**
 * Created by Rafa on 02.08.2015.
 */
public class DriverView {
    Driver driver;
    List<Driver> friends;
    List<OrderView> route;

    String truckId;
    Integer orderId;


    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<OrderView> getRoute() {
        return route;
    }

    public void setRoute(List<OrderView> route) {
        this.route = route;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Driver> getFriends() {
        return friends;
    }

    public void setFriends(List<Driver> friends) {
        this.friends = friends;
    }


    @Override
    public String toString() {
        return "DriverView{" +
                "driver=" + driver +
                ", friends=" + friends +
                ", route=" + route +
                ", truckId='" + truckId + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
