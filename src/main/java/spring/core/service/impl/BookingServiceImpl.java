package spring.core.service.impl;

import spring.core.dao.TicketsDAO;
import spring.core.data.Event;
import spring.core.data.Price;
import spring.core.data.Seat;
import spring.core.data.Ticket;
import spring.core.data.User;
import spring.core.service.AuditoriumService;
import spring.core.service.BookingService;
import spring.core.service.DiscountService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    DiscountService discountService;

    @Autowired
    TicketsDAO ticketsDAO;

    @Override
    public Price getTicketPrice(final Event event, final Date date, final Seat seat, final User user) {
        Price basePrice = event.getBasePrice();

        Double basePriceDouble = basePrice.getValue();
        basePriceDouble = basePriceDouble*seat.getPriceIncrement();
        basePriceDouble = basePriceDouble*event.getRating().getPriceIncrement();

        Double discount = discountService.getDiscount(user, event, date).getValue();

        basePriceDouble = basePriceDouble*discount;

        return new Price(basePrice.getCurrency(), basePriceDouble);
    }

    @Override
    public void bookTicket(final User user, final Ticket ticket) { //TODO add validator interceptor to handle "Already booked ticket"
        ticketsDAO.addTicket(user, ticket);
    }

    @Override
    public List<Ticket> getTicketsForEvent(final Event event, final Date date) {
        return ticketsDAO.getAllTickets(event, date);
    }

    @Override
    public List<Ticket> getTicketsForUser(final User user) {
        return ticketsDAO.getTicketsForUser(user);
    }
}
