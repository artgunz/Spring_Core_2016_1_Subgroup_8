package spring.core.service.impl;

import spring.core.dao.UserDAO;
import spring.core.data.Ticket;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.service.TicketService;
import spring.core.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    TicketService ticketService;

    public User register(final UserRegistrationInformation registrationInformation) {
        return userDAO.createUser(registrationInformation);
    }

    public void remove(final User user) {
        userDAO.deleteUser(user.getId());
    }

    public User getById(final Long id) {
        return userDAO.searchUserById(id);
    }

    public User getUserByEmail(final String email) {
        return userDAO.searchUserByEmail(email);
    }

    public List<User> getUsersByName(final String name) {
        return userDAO.searchUsersByName(name);
    }
}
