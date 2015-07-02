package businessLogic;

import model.Baggage;

/**
 * Created by Rafa on 02.07.2015.
 */
public class DriverPageView  implements Comparable<DriverPageView>{
    private int orderId;
    private String city;
    private int baggageId;
    private int baggageStatus;
    private Baggage baggage;

    private int number;

    public DriverPageView(int orderId, String city, int baggageId, int baggageStatus, Baggage baggage, int number) {
        this.orderId = orderId;
        this.city = city;
        this.baggageId = baggageId;
        this.baggageStatus = baggageStatus;
        this.baggage = baggage;
        this.number = number;
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


    public int compareTo(DriverPageView d) {
        return(number - d.number);
    }
}
