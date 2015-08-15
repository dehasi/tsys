package model;


import model.statuses.DriverStatus;

import javax.persistence.*;
import java.util.List;


/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Table(name = "driver")
@NamedQueries({
        @NamedQuery(
            name = "Driver.getDriverFriends",
            query = "SELECT d FROM Driver d WHERE d.orderRoute = " +
                        "(SELECT dd.orderRoute FROM Driver dd WHERE dd.id = :did)"),

        @NamedQuery(
            name = "Driver.getD",
            query = "SELECT d FROM Driver d" ),

        @NamedQuery(
            name = "Driver.getFree",
            query = "SELECT d FROM Driver d WHERE d.orderRoute = NULL" ),

        @NamedQuery(
                name = "Driver.getForOrder",
                query = "SELECT d FROM Driver d WHERE d.orderRoute = NULL AND d.hoursWorked <= :hw" ),

        @NamedQuery(
            name = "Driver.getInOrder",
            query = "SELECT d FROM Driver d WHERE d.orderRoute <> NULL" ),
})
public class Driver implements Comparable<Driver>{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "hoursWorked")
    private int hoursWorked;

    @Column(name = "status")
    private DriverStatus status;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

//    @JoinColumn(name = "order", nullable = true, referencedColumnName = "order")
//    @ManyToMany
//    private List<OrderRoute> orderRoute;
    @Basic
    @Column(name = "order", nullable = true)
    private Integer orderRoute;
//    private OrderRoute orderRoute;

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
    @Column(name = "hoursworked")
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

    public Integer getOrderRoute() {
        return orderRoute;
    }

    public void setOrderRoute(Integer orderRoute) {
        this.orderRoute = orderRoute;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (hoursWorked != driver.hoursWorked) return false;
        if (!name.equals(driver.name)) return false;
        if (!lastName.equals(driver.lastName)) return false;
        if (status != driver.status) return false;
        if (!city.equals(driver.city)) return false;
        return !(orderRoute != null ? !orderRoute.equals(driver.orderRoute) : driver.orderRoute != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + hoursWorked;
        result = 31 * result + status.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + (orderRoute != null ? orderRoute.hashCode() : 0);
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

    @Override
    public int compareTo(Driver o) {
        return this.id-o.id;
    }
}
