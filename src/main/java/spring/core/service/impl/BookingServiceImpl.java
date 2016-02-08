package spring.core.service.impl;

import spring.core.dao.TicketsDAO;
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

    @Override
    public Price getTicketPrice(final Event event, final Date date, final Seat seat, final User user) {
        Price basePrice = event.getBasePrice();

        Double basePriceDouble = basePrice.getValue();

        auditoriumService.getVipSeats(seat.getAuditorium().getName());
        basePriceDouble = basePriceDouble*seat.getPriceIncrement();
        basePriceDouble = basePriceDouble*event.getRating().getPriceIncrement();

        Double discount = discountService.getDiscount(user, event, date).getValue();

        basePriceDouble = basePriceDouble-discount;

        return new Price(basePrice.getCurrency(), basePriceDouble);
    }

    @Override
    public UserTicket bookTicket(final User user, final TicketCreationInformation ticketCreationInformation) { //TODO add validator interceptor to handle "Already booked ticket"
        Price price = getTicketPrice(ticketCreationInformation.getShowEvent().getEvent(),
                ticketCreationInformation.getShowEvent().getShowTime(),
                ticketCreationInformation.getSeat(), user);

        UserTicket userTicket = new UserTicket(ticketCreationInformation.getShowEvent(), ticketCreationInformation.getSeat(), user, price);

        ticketsDAO.addTicket(user, userTicket);

        UserStatistic userStatistic = new UserStatistic();
        if( user.getUserStatistic()!=null){
            userStatistic = user.getUserStatistic();
        }

        userStatistic.setTicketsNumber(userStatistic.getTicketsNumber()+1);
        user.setUserStatistic(userStatistic);

        LOGGER.info("Booked ticket {}", userTicket);

        return userTicket;
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
