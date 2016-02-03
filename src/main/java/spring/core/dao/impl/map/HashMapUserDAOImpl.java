package spring.core.dao.impl.map;

import spring.core.dao.UserDAO;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.populator.Populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("hashUserDao")
public class HashMapUserDAOImpl implements UserDAO {
    private final static List<User> userStorage = new ArrayList<User>();

    @Autowired
    @Qualifier("userRegistrationPopulator")
    Populator<UserRegistrationInformation, User> userRegistrationPopulator;

    public User createUser(final UserRegistrationInformation registrationInformation) {
        final User user = new User();

        userRegistrationPopulator.populate(registrationInformation, user);

        userStorage.add(user);

        return user;
    }

    public void deleteUser(final Long userId) {
        User user = searchUserById(userId);
        userStorage.remove(user);
    }

    public User searchUserByEmail(final String userEmail) {
        for (final User user : userStorage) {
            if (user.getEmail().equals(userEmail)) {
                return user;
            }
        }
        return null;
    }

    public User searchUserById(final Long userId) {
        for (final User user : userStorage) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public List<User> searchUsersByName(final String userName) {
        final List<User> result = new ArrayList<User>();

        for (final User user : userStorage) {
            if (user.getName().equals(userName)) {
                result.add(user);
            }
        }

        return result;
    }
}
