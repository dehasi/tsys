package model;

/**
 * Created by Rafa on 20.06.2015.
 */
import model.statuses.TruckStatus;

import javax.persistence.*;
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @Column(name = "id_driver")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "worked_hours")
    private long worked;

    @Column(name = "status")
    private TruckStatus status;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne
    @JoinColumn(name = "truck")
    private Truck truck;

    public Driver() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getWorked() {
        return worked;
    }

    public void setWorked(long worked) {
        this.worked = worked;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
