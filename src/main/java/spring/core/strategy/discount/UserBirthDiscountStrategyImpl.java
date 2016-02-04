package spring.core.strategy.discount;

import spring.core.data.Discount;
import spring.core.data.Event;
import spring.core.data.User;

import java.util.Date;

public class UserBirthDiscountStrategyImpl implements DiscountStrategy {
    @Override
    public Discount calculateDiscount(final User user, final Event event, final Date date) {
        return new Discount(0.0); //TODO IMPLEMENT
    }
}
