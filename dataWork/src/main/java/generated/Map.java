package generated;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
public class Map {
    private int cityA;
    private int cityB;
    private int distance;

    @Basic
    @Column(name = "cityA")
    public int getCityA() {
        return cityA;
    }

    public void setCityA(int cityA) {
        this.cityA = cityA;
    }

    @Basic
    @Column(name = "cityB")
    public int getCityB() {
        return cityB;
    }

    public void setCityB(int cityB) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        if (cityA != map.cityA) return false;
        if (cityB != map.cityB) return false;
        if (distance != map.distance) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cityA;
        result = 31 * result + cityB;
        result = 31 * result + distance;
        return result;
    }
}
