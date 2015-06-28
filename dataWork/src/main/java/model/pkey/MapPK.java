package model.pkey;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by Rafa on 28.06.2015.
 */
public class MapPK implements Serializable {
    @Column(name ="cityA")
    private long cityA;

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
        return "MapPK{" +
                "cityA=" + cityA +
                ", cityB=" + cityB +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapPK mapPK = (MapPK) o;

        if (cityA != mapPK.cityA) return false;
        return cityB == mapPK.cityB;

    }

    @Override
    public int hashCode() {
        int result = (int) (cityA ^ (cityA >>> 32));
        result = 31 * result + (int) (cityB ^ (cityB >>> 32));
        return result;
    }
}
