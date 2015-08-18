package model;

import model.statuses.LoadStatus;
import model.statuses.DoneStatus;

import javax.persistence.*;


/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Table(name = "orderroute")
@NamedQueries({
        @NamedQuery(
            name = "OrderRoute.getRoute",
            query = "SELECT r FROM OrderRoute r WHERE r.orderId = :oid"
        ),

        @NamedQuery(
            name = "OrderRoute.getTruck",
            query = "SELECT r.truck FROM OrderRoute r WHERE r.orderId = :oid"
        ),
        @NamedQuery(
            name = "OrderRoute.getAllId",
            query = "SELECT DISTINCT r.orderId FROM OrderRoute r "
        ),

        @NamedQuery(
            name = "OrderRoute.getOrderStatus",
            query = "SELECT DISTINCT r.orderStatus FROM OrderRoute r  WHERE r.orderId = :oid "
        ),
        @NamedQuery(
                name = "OrderRoute.maxId",
                query = "SELECT MAX (r.id) FROM OrderRoute r  "
        ),
        @NamedQuery(
             name = "OrderRoute.maxOrderId",
             query = "SELECT MAX (r.orderId) FROM OrderRoute r  "
        )
})
public class OrderRoute implements Comparable<OrderRoute> {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "orderId")
    private int orderId;

    @ManyToOne
    @JoinColumn(name="city", referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name="baggage" , referencedColumnName = "id")
    private Baggage baggage;

    @Basic
    @Column(name = "loadStatus")
    private LoadStatus loadStatus;    //* load unload

    @Basic
    @Column(name = "isBaggageDone")
    private DoneStatus isBaggageDone; //* is load or unload done

    @Basic
    @Column(name = "visitNumber")
    private int visitNumber;

    @Basic
    @Column(name = "orderStatus")
    private DoneStatus orderStatus; //* orderId status

    @ManyToOne
    @JoinColumn(name="truck", referencedColumnName = "id")
    private Truck truck;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", orderId=" + orderId +
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
        if (orderId == o.orderId) {
            return visitNumber - o.visitNumber;
        }else {
            return orderId - o.orderId;
        }
    }
}
