package spring.core.service.impl;

import spring.core.dao.UserDAO;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.data.UserStatistic;
import spring.core.service.UserService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserDAO userDAO;

    public User register(final UserRegistrationInformation registrationInformation) {
        User existUser = getUserByEmail(registrationInformation.getUserEmail());
        if(existUser!=null){
            LOGGER.warn("User with email {} exists!", registrationInformation.getUserEmail());
            return existUser;
        }

        return userDAO.createUser(registrationInformation);
    }

    public void remove(final User user) {
        userDAO.deleteUser(user.getPk());
    }

    public User getById(final Long id) {
        return userDAO.searchUserByPk(id);
    }

    public User getUserByEmail(final String email) {
        return userDAO.searchUserByEmail(email);
    }

    public List<User> getUsersByName(final String name) {
        return userDAO.searchUsersByName(name);
    }

    @Override
    public void updateUserStatisticByEmail(String userEmail, UserStatistic userStatistic){
        User existUser = getUserByEmail(userEmail);
        if(existUser==null){
            LOGGER.warn("User with email {} not exists!", userEmail);
            return;
        }

        UserStatistic existUserStatistic = existUser.getUserStatistic();
        if(existUserStatistic!=null){
            if(!CollectionUtils.isEmpty(userStatistic.getLuckyEvents())){
                existUserStatistic.getLuckyEvents().addAll(userStatistic.getLuckyEvents());
            }

            if(userStatistic.getTicketsNumber()!=null && userStatistic.getTicketsNumber()!=0){
                existUserStatistic.setTicketsNumber(userStatistic.getTicketsNumber());
            }
        }

        existUser.setUserStatistic(existUserStatistic);

        userDAO.updateUser(existUser);
    }
}
