package spring.core.dao;

import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.data.UserStatistic;

import java.util.List;

public interface UserDAO {

    User createUser(UserRegistrationInformation registrationInformation);

    void deleteUser(Long userId);

    User searchUserByEmail(String userEmail);

    User searchUserByPk(Long userId);

    List<User> searchUsersByName(String userName);
}
