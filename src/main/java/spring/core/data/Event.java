package spring.core.data;

import java.util.Date;
import java.util.List;

import com.sun.istack.internal.Nullable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Event {
    final String name;
    final Price basePrice;
    final Rating rating;

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
}
