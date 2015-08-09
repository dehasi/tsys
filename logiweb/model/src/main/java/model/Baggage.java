package model;

import model.statuses.BaggageStatus;

import javax.persistence.*;

/**
 *  Baggage Entity
 */
@Entity
@Table(name="baggage")
@NamedQueries({
        @NamedQuery(
                name = "Baggage.getMaxId",
                query = "SELECT MAX (b.id) FROM Baggage b"
        )
})

public class Baggage {
    private int id;
    private String name;
    private int weight;
    private BaggageStatus status;

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Baggage baggage = (Baggage) o;

        if (id != baggage.id) return false;
        if (weight != baggage.weight) return false;
        if (name != null ? !name.equals(baggage.name) : baggage.name != null) return false;
        return status == baggage.status;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
