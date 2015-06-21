/**
 * Created by Rafa on 19.06.2015.
 */

package model;

import model.statuses.CargoStatus;

import javax.persistence.*;


@Entity
@Table(name="\"cargo\"")
public class Cargo {
    @Id
    //@GeneratedValue(strategy=GenerationType.)
    @Column(name= "id_cargo")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private long weight;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL) //?
    private CargoStatus status;

    public Cargo() {

    }

    public Cargo(long id, String name, long weight, CargoStatus status) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.status = status;
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

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public CargoStatus getStatus() {
        return status;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }
}
