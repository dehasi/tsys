package businessLogic;

import model.Baggage;

/**
 * Class contains all info about order
 * like id, truck, and set of baggages
 */
public class OrderView implements Comparable<OrderView>{
    private int orderId;
    private String city;
    private int baggageId;
    private int baggageStatus;
    private Baggage baggage;
    private int isDone;

    private int number;

    private String truckId;

    public OrderView(int orderId, String city, int baggageId, int baggageStatus, Baggage baggage, int number, int isDone, String truckId) {
        this.orderId = orderId;
        this.city = city;
        this.baggageId = baggageId;
        this.baggageStatus = baggageStatus;
        this.baggage = baggage;
        this.number = number;
        this.isDone = isDone;
        this.truckId = truckId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(int baggageId) {
        this.baggageId = baggageId;
    }

    public int getBaggageStatus() {
        return baggageStatus;
    }

    public void setBaggageStatus(int baggageStatus) {
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

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    @Override
    public String toString() {
        return "OrderView{" +
                "orderId=" + orderId +
                ", city='" + city + '\'' +
                ", baggageId=" + baggageId +
                ", baggageStatus=" + baggageStatus +
                ", baggage=" + baggage +
                ", isDone=" + isDone +
                ", number=" + number +
                ", truckId='" + truckId + '\'' +
                '}';
    }
}
