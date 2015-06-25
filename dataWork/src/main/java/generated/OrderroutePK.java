package generated;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Rafa on 25.06.2015.
 */
public class OrderroutePK implements Serializable {
    private int orderId;
    private int city;
    private int baggage;

    @Column(name = "order_id")
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

        OrderroutePK that = (OrderroutePK) o;

        if (orderId != that.orderId) return false;
        if (city != that.city) return false;
        if (baggage != that.baggage) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + city;
        result = 31 * result + baggage;
        return result;
    }
}
