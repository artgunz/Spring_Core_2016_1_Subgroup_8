package spring.core.service;

import spring.core.data.Ticket;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;

import java.util.List;

import org.springframework.stereotype.Service;

public interface UserService {

    User register(UserRegistrationInformation registrationInformation);

    void remove(User user);

    User getById(Long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);
}
