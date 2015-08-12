package model;

import model.pkey.OrderPK;
import model.statuses.OrderStatus;

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
//    private int order;
//    private int city;
//    private int baggage;
//    private int type;
//    private int isDone;
//    private int visitNumber;
//    private int status;
//    private String truck;
//


    private int order;
    private City city;
    private Baggage baggage;
    private int type;
    private int isDone;
    private int visitNumber;
    private OrderStatus status;
    private Truck truck;

    @Id
    @Column(name = "order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Id
    @JoinColumn(name = "city")
    @OneToMany
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Id
    @JoinColumn(name = "baggage")
    @OneToMany
    public Baggage getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage baggage) {
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
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @JoinColumn(name = "truck")
    @OneToMany
    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
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
