package spring.core.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserStatistic {
    Integer ticketsNumber = 0;

    public Integer getTicketsNumber() {
        return ticketsNumber;
    }

    public void setTicketsNumber(final Integer ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ticketsNumber", ticketsNumber)
                .toString();
    }
}
