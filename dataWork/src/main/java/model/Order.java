package model;

import model.statuses.OrderStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rafa on 21.06.2015.
 */
@Entity
@Table
public class Order {
    @Id
    @Column
    private long id;
    @Column
    private OrderStatus status;
    @Column
    private List<Route> routes;
    @OneToOne
    @JoinColumn(name="truck") //TODO: make foreign key from order to truck
    private Truck truck;
    @Column
    private List<Driver> drivers;

}
