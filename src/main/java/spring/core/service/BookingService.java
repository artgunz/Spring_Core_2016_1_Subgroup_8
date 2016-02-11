package spring.core.service;

import spring.core.aop.annotation.Countable;
import spring.core.aop.annotation.Loggable;
import spring.core.aop.handler.impl.DefaultCountableMethodHandler;
import spring.core.data.Event;
import spring.core.data.Price;
import spring.core.data.Seat;
import spring.core.data.Ticket;
import spring.core.data.TicketCreationInformation;
import spring.core.data.User;
import spring.core.data.UserTicket;

import java.util.Date;
import java.util.List;

public interface BookingService {
    Price getTicketPrice(Event event, Date date, Seat seat, User user);

    UserTicket bookTicket(User user, Price price, TicketCreationInformation ticketCreationInformation);

    List<Ticket> getTicketsForEvent(Event event, Date date);

    List<Ticket> getTicketsForUser(User user);
}
