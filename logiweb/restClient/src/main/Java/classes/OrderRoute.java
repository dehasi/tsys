package classes;

import classes.data.Baggage;
import classes.data.City;
import classes.data.Truck;
import classes.data.statuses.DoneStatus;
import classes.data.statuses.LoadStatus;


public class OrderRoute implements Comparable<OrderRoute> {
    private int id;

    private int order;

    private City city;

    private Baggage baggage;


    private LoadStatus loadStatus;    //* load unload


    private DoneStatus isBaggageDone; //* is load or unload done

    private int visitNumber;

    private DoneStatus orderStatus; //* order status

    private Truck truck;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }



    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage baggage) {
        this.baggage = baggage;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public LoadStatus getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(LoadStatus loadStatus) {
        this.loadStatus = loadStatus;
    }

    public DoneStatus getIsBaggageDone() {
        return isBaggageDone;
    }

    public void setIsBaggageDone(DoneStatus isBaggageDone) {
        this.isBaggageDone = isBaggageDone;
    }

    public DoneStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(DoneStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }


    @Override
    public String toString() {
        return "OrderRoute{" +
                "id=" + id +
                ", order=" + order +
                ", city=" + city +
                ", baggage=" + baggage +
                ", loadStatus=" + loadStatus +
                ", isBaggageDone=" + isBaggageDone +
                ", visitNumber=" + visitNumber +
                ", orderStatus=" + orderStatus +
                ", truck=" + truck +
                '}';
    }

    @Override
    public int compareTo(OrderRoute o) {
        if (order == o.order) {
            return visitNumber - o.visitNumber;
        }else {
            return order - o.order;
        }
    }
}
