package businessLogic;


/**
 * Class represent orders tickets
 * where baggage have to loan or unload
 */
public class BaggageRouteView {
    private int id;
    private String name;

    private String loadCity;
    private int loadStatus;

    private String unloadCity;
    private int unloadStatus;

    private int orderId;
    private String truckId;

    private int orderStatus;

    private int visitNubber;

    public BaggageRouteView(int id, String name, String loadCity, int loadStatus, String unloadCity, int unloadStatus,
                            int orderId, String truckId, int orderStatus, int visitNubber) {
        this.id = id;
        this.name = name;
        this.loadCity = loadCity;
        this.loadStatus = loadStatus;
        this.unloadCity = unloadCity;
        this.unloadStatus = unloadStatus;
        this.orderId = orderId;
        this.truckId = truckId;
        this.orderStatus = orderStatus;
        this.visitNubber = visitNubber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoadCity() {
        return loadCity;
    }

    public void setLoadCity(String loadCity) {
        this.loadCity = loadCity;
    }

    public int getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(int loadStatus) {
        this.loadStatus = loadStatus;
    }

    public String getUnloadCity() {
        return unloadCity;
    }

    public void setUnloadCity(String unloadCity) {
        this.unloadCity = unloadCity;
    }

    public int getUnloadStatus() {
        return unloadStatus;
    }

    public void setUnloadStatus(int unloadStatus) {
        this.unloadStatus = unloadStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getVisitNubber() {
        return visitNubber;
    }

    public void setVisitNubber(int visitNubber) {
        this.visitNubber = visitNubber;
    }
}
