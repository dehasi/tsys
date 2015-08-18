package model.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Embeddable
public class Cities implements Serializable {
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

    @Override
    public String toString() {
        return "Cities{" +
                "cityA=" + cityA +
                ", cityB=" + cityB +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cities cities = (Cities) o;

        if (cityA != cities.cityA) return false;
        return cityB == cities.cityB;

    }

    @Override
    public int hashCode() {
        int result = (int) (cityA ^ (cityA >>> 32));
        result = 31 * result + (int) (cityB ^ (cityB >>> 32));
        return result;
    }
}
