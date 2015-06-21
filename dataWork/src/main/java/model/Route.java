package model;

import model.statuses.OrderStatus;
import model.statuses.RouteStatus;

import javax.persistence.*;

/**
 * Created by Rafa on 21.06.2015.
 */
@Entity
@Table
public class Route {
    @Id
    @Column(name = "id")
    private long id;
    @JoinColumn(name = "city")
    private City city;
    @JoinColumn(name = "cargo")
    private Cargo cargo;
    @Column(name = "status")
    private RouteStatus routeStatus;
    @Column(name = "is_done")
    private OrderStatus doneStatus;
}
