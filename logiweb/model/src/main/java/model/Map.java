package model;


import model.pkey.MapPK;

import javax.persistence.*;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Table(name = "map")
@IdClass(MapPK.class)
public class Map {
    private int distance;

    @Id
    @Column(name ="cityA")
    private long cityA;

    @Id
    @Column(name = "cityB")
    private long cityB;

    public long getCityA() {
        return cityA;
    }

    public void setCityA(long cityA) {
        this.cityA = cityA;
    }

    public long getCityB() {
        return cityB;
    }

    public void setCityB(long cityB) {
        this.cityB = cityB;
    }

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
                ", cityA=" + cityA +
                ", cityB=" + cityB +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        if (distance != map.distance) return false;
        if (cityA != map.cityA) return false;
        return cityB == map.cityB;

    }

    @Override
    public int hashCode() {
        int result = distance;
        result = 31 * result + (int) (cityA ^ (cityA >>> 32));
        result = 31 * result + (int) (cityB ^ (cityB >>> 32));
        return result;
    }
}
