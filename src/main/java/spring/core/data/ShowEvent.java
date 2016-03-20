package spring.core.data;

import spring.core.data.db.Item;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "show_event")
public class ShowEvent extends Item implements Serializable {

    @OneToOne
    @JoinColumn(name="event_pk")
    Event event;

    @OneToOne
    @JoinColumn(name="auditorium_pk")
    Auditorium auditorium;

    @Column(name = "show_date")
    Date showTime;

    public ShowEvent() {
    }

    public ShowEvent(final Event event, final Auditorium auditorium, final Date showTime) {
        this.event = event;
        this.auditorium = auditorium;
        this.showTime = showTime;
    }

    public Event getEvent() {
        return event;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public Date getShowTime() {
        return showTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof ShowEvent)) return false;

        final ShowEvent showEvent = (ShowEvent) o;

        return new EqualsBuilder()
                .append(event, showEvent.event)
                .append(auditorium, showEvent.auditorium)
                .append(showTime, showEvent.showTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(event)
                .append(auditorium)
                .append(showTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("event", event)
                .append("auditorium", auditorium)
                .append("showTime", showTime)
                .toString();
    }
}
