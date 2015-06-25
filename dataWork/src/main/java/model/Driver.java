package model;

import model.statuses.DriverStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "last_name")
    private String lastName;

    private int hoursWorked;

    private DriverStatus status;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;


    @ManyToMany
    @JoinColumn(name = "order_id")
    private Set<OrderRoute> orderRoute;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Basic
    @Column(name = "hours_worked")
    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Basic
    @Column(name = "status")
    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public Set<OrderRoute> getOrderRoute() {
        return orderRoute;
    }

    public void setOrderRoute(Set<OrderRoute> orderRoute) {
        this.orderRoute = orderRoute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (hoursWorked != driver.hoursWorked) return false;
        if (status != driver.status) return false;
        if (name != null ? !name.equals(driver.name) : driver.name != null) return false;
        if (lastName != null ? !lastName.equals(driver.lastName) : driver.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + hoursWorked;

        return result;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", status=" + status +
                ", city=" + city +
                ", orderRoute=" + orderRoute +
                '}';
    }
}
