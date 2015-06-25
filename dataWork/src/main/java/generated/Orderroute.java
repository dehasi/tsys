package generated;

import javax.persistence.*;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@IdClass(OrderroutePK.class)
public class Orderroute {
    private int orderId;
    private int city;
    private int baggage;
    private int type;
    private int isDone;
    private int visitNumber;
    private int status;
    private String truck;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "city")
    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    @Id
    @Column(name = "baggage")
    public int getBaggage() {
        return baggage;
    }

    public void setBaggage(int baggage) {
        this.baggage = baggage;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "is_done")
    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    @Basic
    @Column(name = "visit_number")
    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "truck")
    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderroute that = (Orderroute) o;

        if (orderId != that.orderId) return false;
        if (city != that.city) return false;
        if (baggage != that.baggage) return false;
        if (type != that.type) return false;
        if (isDone != that.isDone) return false;
        if (visitNumber != that.visitNumber) return false;
        if (status != that.status) return false;
        if (truck != null ? !truck.equals(that.truck) : that.truck != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + city;
        result = 31 * result + baggage;
        result = 31 * result + type;
        result = 31 * result + isDone;
        result = 31 * result + visitNumber;
        result = 31 * result + status;
        result = 31 * result + (truck != null ? truck.hashCode() : 0);
        return result;
    }
}
