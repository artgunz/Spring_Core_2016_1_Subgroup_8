package spring.core.dao;

import spring.core.data.Event;
import spring.core.data.Ticket;
import spring.core.data.User;

import java.util.Date;
import java.util.List;

public interface TicketsDAO {
    Ticket addTicket(User user, Ticket ticket);

    List<Ticket> getAllTickets(Event event, Date date);

    List getTicketsForUser(User user);
}
