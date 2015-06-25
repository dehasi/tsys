package model;

import model.embedded.Cities;

import javax.persistence.*;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Table(name = "map")
public class Map {
    private int distance;

   @EmbeddedId
   private Cities cities;

    @Basic
    @Column(name = "distance")
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Map{" +
                "distance=" + distance +
                ", cities=" + cities +
                '}';
    }
}
