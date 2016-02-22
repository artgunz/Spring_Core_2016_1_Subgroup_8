package spring.core.data;

public class TicketCreationInformation {
    ShowEvent showEvent;
    Seat seat;

    public ShowEvent getShowEvent() {
        return showEvent;
    }

    public void setShowEvent(final ShowEvent showEvent) {
        this.showEvent = showEvent;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(final Seat seat) {
        this.seat = seat;
    }
}
