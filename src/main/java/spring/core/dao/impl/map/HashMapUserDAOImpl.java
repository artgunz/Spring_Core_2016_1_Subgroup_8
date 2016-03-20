package spring.core.dao.impl.map;

import spring.core.dao.UserDAO;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.populator.Populator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class HashMapUserDAOImpl implements UserDAO {
    private final static Set<User> userStorage = new HashSet<User>();

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
        User user = searchUserByPk(userId);
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

    public User searchUserByPk(final Long userId) {
        for (final User user : userStorage) {
            if (user.getPk().equals(userId)) {
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
