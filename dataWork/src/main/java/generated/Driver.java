package generated;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
public class Driver {
    private int id;
    private String name;
    private String lastName;
    private int hoursWorked;
    private int status;
    private int city;
    private Integer orderId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_name")
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
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "city")
    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    @Basic
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (hoursWorked != driver.hoursWorked) return false;
        if (status != driver.status) return false;
        if (city != driver.city) return false;
        if (name != null ? !name.equals(driver.name) : driver.name != null) return false;
        if (lastName != null ? !lastName.equals(driver.lastName) : driver.lastName != null) return false;
        if (orderId != null ? !orderId.equals(driver.orderId) : driver.orderId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + hoursWorked;
        result = 31 * result + status;
        result = 31 * result + city;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        return result;
    }
}
