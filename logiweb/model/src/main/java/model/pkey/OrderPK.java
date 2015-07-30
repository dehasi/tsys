package model.pkey;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Rafa on 25.06.2015.
 */
public class OrderPK implements Serializable {
    private int order;
    private int city;
    private int baggage;

    @Column(name = "order")
    @Id
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Column(name = "city")
    @Id
    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    @Column(name = "baggage")
    @Id
    public int getBaggage() {
        return baggage;
    }

    public void setBaggage(int baggage) {
        this.baggage = baggage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPK orderPK = (OrderPK) o;

        if (order != orderPK.order) return false;
        if (city != orderPK.city) return false;
        return baggage == orderPK.baggage;

    }

    @Override
    public int hashCode() {
        int result = order;
        result = 31 * result + city;
        result = 31 * result + baggage;
        return result;
    }
}
