package spring.core.data;

import spring.core.data.db.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "user_statistic")
public class UserStatistic extends Item implements Serializable {

    @OneToOne
    @JoinColumn(name="user_pk")
    User user;

    @Column(name = "user_tickets_count")
    Integer ticketsNumber = 0;

    @ElementCollection
    @CollectionTable(
            name="lucky_events_relation",
            joinColumns=@JoinColumn(name="user_statistic_pk")
    )
    @Column(name="lucky_event_pk") //TODO not working
    List<Event> luckyEvents = new ArrayList<>();

    public UserStatistic() {
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ticketsNumber", ticketsNumber)
                .toString();
    }
}
