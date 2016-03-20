package spring.core.populator.impl;

import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.populator.Populator;
import spring.core.strategy.UserIdGeneratorStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationPopulator implements Populator<UserRegistrationInformation, User> {

    @Autowired
    UserIdGeneratorStrategy userIdGeneratorStrategy;

    public void populate(final UserRegistrationInformation source, final User target) throws ConversionException {
        target.setEmail(source.getUserEmail());
        target.setName(source.getUserName());
        target.setPk(userIdGeneratorStrategy.generateId());
    }
}
