package businessLogic;

import model.Baggage;
import model.City;
import model.Truck;
import model.statuses.DoneStatus;

/**
 * Just POJO class
 * Class contains all info about order
 * like id, truck, and set of baggages
 */
public class OrderView implements Comparable<OrderView>{
    private int orderId;
    private City city;
    private DoneStatus baggageStatus;
    private Baggage baggage;
    private DoneStatus isDone;

    private int number;

    private Truck truck;

    public OrderView(int orderId, City city, Baggage baggage, DoneStatus baggageStatus, int number, DoneStatus isDone, Truck truck) {
        this.orderId = orderId;
        this.city = city;
        this.baggage = baggage;
        this.baggageStatus = baggageStatus;
        this.number = number;
        this.isDone = isDone;
        this.truck = truck;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public DoneStatus getBaggageStatus() {
        return baggageStatus;
    }

    public void setBaggageStatus(DoneStatus baggageStatus) {
        this.baggageStatus = baggageStatus;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage baggage) {
        this.baggage = baggage;
    }


    public int compareTo(OrderView d) {
        return(number - d.number);
    }

    public DoneStatus getIsDone() {
        return isDone;
    }

    public void setIsDone(DoneStatus isDone) {
        this.isDone = isDone;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "OrderView{" +
                "orderId=" + orderId +
                ", city='" + city + '\'' +
                ", baggageId=" + baggage +
                ", baggageStatus=" + baggageStatus +
                ", baggage=" + baggage +
                ", isDone=" + isDone +
                ", number=" + number +
                ", truckId='" + truck + '\'' +
                '}';
    }
}
