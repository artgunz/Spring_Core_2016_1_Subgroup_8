package spring.core.data;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class ShowEvent{
    final Event event;
    final Auditorium auditorium;
    final Date showTime;

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
