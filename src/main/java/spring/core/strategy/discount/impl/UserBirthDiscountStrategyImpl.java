package spring.core.strategy.discount.impl;

import spring.core.data.Discount;
import spring.core.data.Event;
import spring.core.data.User;
import spring.core.strategy.discount.DiscountStrategy;

import java.util.Date;

public class UserBirthDiscountStrategyImpl implements DiscountStrategy {
    @Override
    public Discount calculateDiscount(final User user, final Event event, final Date date) {
        return new Discount(0.0); //TODO IMPLEMENT
    }
}
