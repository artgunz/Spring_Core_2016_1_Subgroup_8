package spring.core.facade;

import spring.core.data.Ticket;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;

import java.util.List;

public interface UserFacade {

    User register(UserRegistrationInformation registrationInformation);

    void remove(User user);

    User getById(Long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    List<Ticket> getBookedTickets(User user);

}
