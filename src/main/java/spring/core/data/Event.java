package spring.core.data;

import spring.core.data.db.Item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "event")
public class Event extends Item implements Serializable {
    @Column(name = "event_name", nullable = false)
    String name;

    @Embedded
    Price basePrice;

    @Embedded
    Rating rating;

    public Event() {
    }

    public Event(final String name, final Price basePrice, final Rating rating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public Price getBasePrice() {
        return basePrice;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Event)) return false;

        final Event event = (Event) o;

        return new EqualsBuilder()
                .append(name, event.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("basePrice", basePrice)
                .append("rating", rating)
                .toString();
    }
}
