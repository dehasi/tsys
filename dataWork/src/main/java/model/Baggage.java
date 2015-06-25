package model;

import model.statuses.BaggageStatus;

import javax.persistence.*;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Table(name="baggage")
public class Baggage {
    private int id;
    private String name;
    private int weight;
    private BaggageStatus status;

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
    @Column(name = "weight")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "status")
    public BaggageStatus getStatus() {
        return status;
    }

    public void setStatus(BaggageStatus status) {
        this.status = status;
    }

    public Baggage() {
    }

    public Baggage(int id, String name, int weight, BaggageStatus status) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", status=" + status +
                '}';
    }
}
