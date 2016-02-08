package spring.core.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Seat {
    final Auditorium auditorium;
    final Integer id;
    Double priceIncrement;

    public Seat(final Auditorium auditorium, final Integer id) {
        this.auditorium = auditorium;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Double getPriceIncrement() {
        if(priceIncrement ==null && getAuditorium().getVipSeats().contains(getId())){
            this.priceIncrement = 1.5;
        }else{
            priceIncrement = 1.0;
        }

        return priceIncrement;
    }

    public Auditorium getAuditorium() {
        return auditorium;
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
