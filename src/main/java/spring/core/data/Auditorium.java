package spring.core.data;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Auditorium {
    final String name;
    final Integer seatsCount;
    List<Integer> vipSeats;

    public Auditorium(final String name, final Integer seatsCount) {
        this.name = name;
        this.seatsCount = seatsCount;
    }

    public String getName() {
        return name;
    }

    public Integer getSeatsCount() {
        return seatsCount;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("seatsCount", seatsCount)
                .append("vipSeats", vipSeats)
                .toString();
    }
}
