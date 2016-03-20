package spring.core.data;

import spring.core.data.db.Item;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "auditorium")
public class Auditorium extends Item implements Serializable {

    @Column(name = "auditorium_name", unique = true)
    String name;

    @Column(name = "seat_count")
    Integer seatsCount;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL},
            mappedBy = "auditorium")
    List<Seat> vipSeats;

    public Auditorium() {
    }

    public Auditorium(final String name, final Integer seatsCount) {
        this.name = name;
        this.seatsCount = seatsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(final Integer seatsCount) {
        this.seatsCount = seatsCount;
    }

    public List<Seat> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(final List<Seat> vipSeats) {
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
