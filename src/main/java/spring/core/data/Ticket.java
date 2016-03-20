package spring.core.data;

import spring.core.data.db.Item;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "ticket")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="assigned")
public class Ticket extends Item implements Serializable{

    @OneToOne
    @JoinColumn(name="show_event_pk", nullable = false)
    ShowEvent showEvent;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="seat_pk", nullable = false)
    Seat seat;

    public Ticket() {
    }

    public Ticket(final ShowEvent showEvent, final Seat seat) {
        if (!showEvent.getAuditorium().equals(seat.getAuditorium())) {
            throw new IllegalArgumentException("Seat and event should belong to equal Auditorium!");
        }

        this.showEvent = showEvent;
        this.seat = seat;
    }

    public ShowEvent getShowEvent() {
        return showEvent;
    }

    public Seat getSeat() {
        return seat;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Ticket)) return false;

        final Ticket ticket = (Ticket) o;

        return new EqualsBuilder()
                .append(showEvent, ticket.showEvent)
                .append(seat, ticket.seat)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(showEvent)
                .append(seat)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("showEvent", showEvent)
                .append("seat", seat)
                .toString();
    }
}
