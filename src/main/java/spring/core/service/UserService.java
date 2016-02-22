package spring.core.service;

import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;

import java.util.List;

public interface UserService {

    User register(UserRegistrationInformation registrationInformation);

    void remove(User user);

    User getById(Long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);
}
