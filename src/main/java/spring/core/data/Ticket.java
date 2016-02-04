package spring.core.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Ticket {
    final ShowEvent showEvent;
    final Seat seat;

    public Ticket(final ShowEvent showEvent, final Seat seat) {
        if(!showEvent.getAuditorium().equals(seat.getAuditorium())){
            throw  new IllegalArgumentException("Seat and event should belong to equal Auditorium!");
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
}
