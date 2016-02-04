package spring.core.data;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Auditorium {
    String name;
    Integer numberOfSeats;
    List<Integer> vipSeats;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(final Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(final List<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Auditorium)) return false;

        final Auditorium that = (Auditorium) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }
}
