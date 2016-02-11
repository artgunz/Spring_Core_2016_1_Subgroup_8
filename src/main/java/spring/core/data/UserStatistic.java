package spring.core.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserStatistic {
    Integer ticketsNumber = 0;

    List<Event> luckyEvents = new ArrayList<>();

    public Integer getTicketsNumber() {
        return ticketsNumber;
    }

    public void setTicketsNumber(final Integer ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    public List<Event> getLuckyEvents() {
        return luckyEvents;
    }

    public void setLuckyEvents(final List<Event> luckyEvents) {
        this.luckyEvents = luckyEvents;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ticketsNumber", ticketsNumber)
                .toString();
    }
}
