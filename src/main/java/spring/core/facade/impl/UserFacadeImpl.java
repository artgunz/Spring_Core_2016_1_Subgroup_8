package spring.core.facade.impl;

import spring.core.data.Ticket;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.facade.UserFacade;
import spring.core.service.TicketService;
import spring.core.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    public User register(final UserRegistrationInformation registrationInformation) {
        return userService.register(registrationInformation);
    }

    public void remove(final User user) {
        userService.remove(user);
    }

    public User getById(final Long id) {
        return userService.getById(id);
    }

    public User getUserByEmail(final String email) {
        return userService.getUserByEmail(email);
    }

    public List<User> getUsersByName(final String name) {
        return userService.getUsersByName(name);
    }

    public List<Ticket> getBookedTickets(final User user) {
        final List<Ticket> tickets = new ArrayList<Ticket>();

        final List<Ticket> allTicketList = ticketService.getTicketList();
        for(final Ticket ticket : allTicketList){
            if (ticket.getUser().equals(user)){
                tickets.add(ticket);
            }
        }

        return tickets;
    }
}
