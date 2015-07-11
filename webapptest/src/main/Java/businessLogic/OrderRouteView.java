package businessLogic;

import model.OrderRoute;

import java.util.List;

/**
 * Created by Rafa on 07.07.2015.
 */
public class OrderRouteView {
    int orderId;
    int status;
    String turckId;
    List<OrderView> routes;

    public OrderRouteView(int orderId, int status, String turckId, List<OrderView> routes) {
        this.orderId = orderId;
        this.status = status;
        this.turckId = turckId;
        this.routes = routes;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTurckId() {
        return turckId;
    }

    public void setTurckId(String turckId) {
        this.turckId = turckId;
    }

    public List<OrderView> getRoutes() {
        return routes;
    }

    public void setRoutes(List<OrderView> routes) {
        this.routes = routes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderRouteView{" +
                "orderId=" + orderId +
                ", turckId='" + turckId + '\'' +
                ", routes=" + routes +
                '}';
    }
}
