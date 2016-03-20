package spring.core.data;

import spring.core.data.db.Item;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Rating implements Serializable {
    @Column(name = "rating_name")
    String name;
    @Column(name = "rating_price_coefficient")
    Double price;

    public Rating() {
    }

    public Rating(final String name, final Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}
