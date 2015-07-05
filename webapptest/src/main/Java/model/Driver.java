package model;

import com.sun.istack.internal.Nullable;
import model.statuses.DriverStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    private int hoursWorked;

    private DriverStatus status;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @Column(name = "order_id")
    @Nullable
    private Integer orderRoute;

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
        if (status != driver.status) return false;
        if (name != null ? !name.equals(driver.name) : driver.name != null) return false;
        return !(lastName != null ? !lastName.equals(driver.lastName) : driver.lastName != null);

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

    @Override
    public int compareTo(Driver o) {
        return this.id-o.id;
    }
}
