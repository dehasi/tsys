package classes.data;


import classes.data.statuses.*;
public class Truck {
    private String id;
    private long dutySize;
    private long capacity;
    private TruckStatus status;

    private City city;

    private Integer orderId;

    public Truck() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDutySize() {
        return dutySize;
    }

    public void setDutySize(long dutySize) {
        this.dutySize = dutySize;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Truck(String id, long dutySize, long capacity, TruckStatus status, City city) {
        this.id = id;
        this.dutySize = dutySize;
        this.capacity = capacity;
        this.status = status;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id='" + id + '\'' +
                ", dutySize=" + dutySize +
                ", capacity=" + capacity +
                ", status=" + status +
                ", city=" + city +
                '}';
    }
}
