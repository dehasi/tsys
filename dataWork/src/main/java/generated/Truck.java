package generated;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
public class Truck {
    private String id;
    private int dutyTime;
    private int capacity;
    private int status;
    private int city;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "duty_time")
    public int getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(int dutyTime) {
        this.dutyTime = dutyTime;
    }

    @Basic
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (dutyTime != truck.dutyTime) return false;
        if (capacity != truck.capacity) return false;
        if (status != truck.status) return false;
        if (city != truck.city) return false;
        if (id != null ? !id.equals(truck.id) : truck.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + dutyTime;
        result = 31 * result + capacity;
        result = 31 * result + status;
        result = 31 * result + city;
        return result;
    }
}
