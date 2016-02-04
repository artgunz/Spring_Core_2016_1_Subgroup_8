package spring.core.service;

import spring.core.data.Event;
import spring.core.data.Price;
import spring.core.data.Seat;
import spring.core.data.Ticket;
import spring.core.data.User;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    Price getTicketPrice(Event event, Date date, Seat seat, User user);

    void bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(Event event, Date date);
}
