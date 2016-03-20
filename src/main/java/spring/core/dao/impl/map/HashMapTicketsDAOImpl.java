package spring.core.dao.impl.map;

import spring.core.dao.TicketsDAO;
import spring.core.data.Event;
import spring.core.data.Ticket;
import spring.core.data.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class HashMapTicketsDAOImpl implements TicketsDAO {
    private final static Map<User, List<Ticket>> ticketStorage = new HashMap<>();

    @Override
    public Ticket addTicket(User user, Ticket ticket) {
        List<Ticket> ticketsForUser = ticketStorage.get(user);

        if (ticketsForUser == null) {
            ticketsForUser = new ArrayList<>();
            ticketStorage.put(user, ticketsForUser);
        }

        ticketsForUser.add(ticket);

        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets(Event event, Date date) {
        List<Ticket> ticketsForEventAndDate = new ArrayList<>();

        for (Map.Entry<User, List<Ticket>> entry : ticketStorage.entrySet()) {
            List<Ticket> tickets = entry.getValue();
            for (Ticket ticket : tickets) {
                if (ticket.getShowEvent().getEvent().equals(event) && ticket.getShowEvent().getShowTime().equals
                        (date)) {
                    ticketsForEventAndDate.add(ticket);
                }
            }
        }

        return ticketsForEventAndDate;
    }

    @Override
    public List<Ticket> getTicketsForUser(User user) {
        return ticketStorage.get(user);
    }
}
