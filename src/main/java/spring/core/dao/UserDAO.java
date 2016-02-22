package spring.core.dao;

import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;

import java.util.List;

public interface UserDAO {

    User createUser(UserRegistrationInformation registrationInformation);

    void deleteUser(Long userId);

    User searchUserByEmail(String userEmail);

    User searchUserById(Long userId);

    List<User> searchUsersByName(String userName);

}
