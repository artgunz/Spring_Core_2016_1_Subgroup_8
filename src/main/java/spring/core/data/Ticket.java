package spring.core.data;

public class Ticket {
    User user;
    Event event;
    Seat seat;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event event) {
        this.event = event;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(final Seat seat) {
        this.seat = seat;
    }
}
