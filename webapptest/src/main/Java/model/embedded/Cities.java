package model.embedded;

import model.City;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * Created by Rafa on 25.06.2015.
 */
@Entity
@Embeddable
public class Cities implements Serializable {
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
        return "Cities{" +
                "cityA=" + cityA +
                ", cityB=" + cityB +
                '}';
    }
}
