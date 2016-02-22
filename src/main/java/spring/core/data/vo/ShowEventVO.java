package spring.core.data.vo;

import java.util.Date;

public class ShowEventVO {
    Long pk;
    Long eventPk;
    String auditorium;
    Date date;

    public Long getPk() {
        return pk;
    }

    public void setPk(final Long pk) {
        this.pk = pk;
    }

    public Long getEventPk() {
        return eventPk;
    }

    public void setEventPk(final Long eventPk) {
        this.eventPk = eventPk;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(final String auditorium) {
        this.auditorium = auditorium;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }
}
