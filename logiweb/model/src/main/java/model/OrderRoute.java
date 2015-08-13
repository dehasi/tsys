package model;

import model.pkey.OrderPK;
import model.statuses.OrderStatus;

import javax.persistence.*;


/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
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
    @Id
    private int id;

    @Column(name = "order")
    private int order;

    @JoinColumn(name = "city")
    @ManyToOne
    private City city;

    @JoinColumn(name = "baggage")
    @ManyToOne
    private Baggage baggage;

    @Basic
    @Column(name = "type")
    private int type;

    @Basic
    @Column(name = "is_done")
    private int isDone;

    @Basic
    @Column(name = "visit_number")
    private int visitNumber;

    @Basic
    @Column(name = "status")
    private OrderStatus status;

    @JoinColumn(name = "truck")
    @ManyToOne
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


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }


    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
                ", type=" + type +
                ", isDone=" + isDone +
                ", visitNumber=" + visitNumber +
                ", status=" + status +
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
