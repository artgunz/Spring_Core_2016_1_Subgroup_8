package spring.core.strategy.discount.impl;

import spring.core.data.Discount;
import spring.core.data.Event;
import spring.core.data.User;
import spring.core.strategy.discount.DiscountStrategy;

import java.util.Date;

public class UserTicketsBoughtDiscountStrategyImpl implements DiscountStrategy {
    @Override
    public Discount calculateDiscount(final User user, final Event event, final Date date) {
        if(user.getUserStatistic()!=null && user.getUserStatistic().getTicketsNumber() == 10){ //TODO change logic
            return new Discount(1.5);
        }
        return new Discount(0.0);
    }
}
