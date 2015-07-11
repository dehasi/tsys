package model;

import model.pkey.OrderPK;

import javax.persistence.*;


/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@IdClass(OrderPK.class)
@NamedQueries({
        @NamedQuery(
            name = "OrderRoute.getRoute",
            query = "SELECT r FROM OrderRoute r WHERE r.order = :id"
        ),

        @NamedQuery(
            name = "OrderRoute.getTruck",
            query = "SELECT r.truck FROM OrderRoute r WHERE r.order = :id"
        ),
        @NamedQuery(
            name = "OrderRoute.getAllId",
            query = "SELECT DISTINCT r.order FROM OrderRoute r "
        ),

        @NamedQuery(
            name = "OrderRoute.getOrderStatus",
            query = "SELECT DISTINCT r.status FROM OrderRoute r  WHERE r.order = :id "
        ),
        @NamedQuery(
                name = "OrderRoute.maxId",
                query = "SELECT MAX (r.order) FROM OrderRoute r  "
        )
})
public class OrderRoute implements Comparable<OrderRoute> {
    private int order;
    private int city;
    private int baggage;
    private int type;
    private int isDone;
    private int visitNumber;
    private int status;
    private String truck;


    @Id
    @Column(name = "order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

        OrderRoute orderRoute1 = (OrderRoute) o;

        if (order != orderRoute1.order) return false;
        if (city != orderRoute1.city) return false;
        if (baggage != orderRoute1.baggage) return false;
        if (type != orderRoute1.type) return false;
        if (isDone != orderRoute1.isDone) return false;
        if (visitNumber != orderRoute1.visitNumber) return false;
        return status == orderRoute1.status;

    }

    @Override
    public int hashCode() {
        int result = order;
        result = 31 * result + city;
        result = 31 * result + baggage;
        result = 31 * result + type;
        result = 31 * result + isDone;
        result = 31 * result + visitNumber;
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "OrderRoute{" +
                "order=" + order +
                ", city=" + city +
                ", baggage=" + baggage +
                ", type=" + type +
                ", isDone=" + isDone +
                ", visitNumber=" + visitNumber +
                ", status=" + status +
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
