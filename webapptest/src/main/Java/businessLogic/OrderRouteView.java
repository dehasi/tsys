package businessLogic;

import model.OrderRoute;

import java.util.List;

/**
 * Created by Rafa on 07.07.2015.
 */
public class OrderRouteView {
    int orderId;
    String turckId;
    List<OrderView> routes;

    public OrderRouteView(int orderIf, String turckId, List<OrderView> routes) {
        this.orderId = orderIf;
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

    @Override
    public String toString() {
        return "OrderRouteView{" +
                "orderId=" + orderId +
                ", turckId='" + turckId + '\'' +
                ", routes=" + routes +
                '}';
    }
}
