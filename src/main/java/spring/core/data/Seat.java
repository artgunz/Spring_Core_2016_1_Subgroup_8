package spring.core.data;

import spring.core.data.db.Item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "seat")
public class Seat extends Item implements Serializable{

    @OneToOne
    Auditorium auditorium;

    @Column(name = "seat_num", nullable = false)
    Integer id;

    @Column(name = "price_increment", nullable = true)
    Double priceIncrement = 0.0;

    public Seat() {
    }

    public Seat(final Auditorium auditorium, final Integer id) {
        this.auditorium = auditorium;
        this.id = id;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(final Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Double getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(final Double priceIncrement) {
        this.priceIncrement = priceIncrement;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Seat)) return false;

        final Seat seat = (Seat) o;

        return new EqualsBuilder()
                .append(auditorium, seat.auditorium)
                .append(id, seat.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(auditorium)
                .append(id)
                .toHashCode();
    }
}
