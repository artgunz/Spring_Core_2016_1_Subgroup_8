package spring.core.service.impl;

import spring.core.aop.annotation.Countable;
import spring.core.aop.annotation.Loggable;
import spring.core.aop.annotation.LoggableAround;
import spring.core.aop.handler.impl.DefaultCountableMethodHandler;
import spring.core.dao.TicketsDAO;
import spring.core.data.Currency;
import spring.core.data.Event;
import spring.core.data.Price;
import spring.core.data.Seat;
import spring.core.data.Ticket;
import spring.core.data.TicketCreationInformation;
import spring.core.data.User;
import spring.core.data.UserStatistic;
import spring.core.data.UserTicket;
import spring.core.service.AuditoriumService;
import spring.core.service.BookingService;
import spring.core.service.DiscountService;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LogManager.getLogger(BookingServiceImpl.class);

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    DiscountService discountService;

    @Autowired
    TicketsDAO ticketsDAO;

    @LoggableAround
    @Override
    public Price getTicketPrice(final Event event, final Date date, final Seat seat, final User user) {
        Price basePrice = event.getBasePrice();
        if(basePrice==null){
            basePrice = new Price(Currency.USD, 0.0D);
        }

        Double basePriceDouble = basePrice.getValue();

        auditoriumService.getVipSeats(seat.getAuditorium().getName());
        basePriceDouble = basePriceDouble*seat.getPriceIncrement();
        basePriceDouble = basePriceDouble*event.getRating().getPriceIncrement();

        Double discount = discountService.getDiscount(user, event, date).getValue();

        basePriceDouble = basePriceDouble-discount;

        return new Price(basePrice.getCurrency(), basePriceDouble);
    }

    @Loggable
    @Countable(handler = DefaultCountableMethodHandler.class)
    @Override
    public UserTicket bookTicket(final User user, final Price price, final TicketCreationInformation ticketCreationInformation) {
        UserTicket userTicket = new UserTicket(ticketCreationInformation.getShowEvent(), ticketCreationInformation.getSeat(), user, price);

        ticketsDAO.addTicket(user, userTicket);

        return userTicket;
    }

    @Override
    public List<Ticket> getTicketsForEvent(final Event event, final Date date) {
        return ticketsDAO.getAllTickets(event, date);
    }

    @Loggable
    @Override
    public List<Ticket> getTicketsForUser(final User user) {
        return ticketsDAO.getTicketsForUser(user);
    }
}
