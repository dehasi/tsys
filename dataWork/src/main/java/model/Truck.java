package model;

import model.statuses.TruckStatus;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;

import static org.hibernate.annotations.CascadeType.*;

/**
 * Created by Rafa on 20.06.2015.
 */
@Entity
@Table(name = "truck")
public class Truck {
    @Id
    @Column(name = "id_truck")
    private String id;
    @Column(name = "duty")
    private long dutySize;
    @Column(name = "capacity")
    private long capacity;
    @Column(name = "status")
    private TruckStatus status;

    @ManyToOne
    @JoinColumn(name="city")
    private City city;

    public Truck() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDutySize() {
        return dutySize;
    }

    public void setDutySize(long dutySize) {
        this.dutySize = dutySize;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
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
}
